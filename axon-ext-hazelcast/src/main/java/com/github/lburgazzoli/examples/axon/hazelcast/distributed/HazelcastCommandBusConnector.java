/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.examples.axon.hazelcast.distributed;

import com.google.common.collect.Sets;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.distributed.CommandBusConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 */
public class HazelcastCommandBusConnector implements CommandBusConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(HazelcastCommandBusConnector.class);

    private final HazelcastCluster m_hazelcastCluster;
    private final CommandBus m_localSegment;
    private final Set<String> m_supportedCommands;
    private String m_nodeId;
    private Integer m_loadFactor;
    private HazelcastCommandListener m_queueListener;

    /**
     * c-tor
     *
     * @param hazelcastCluster
     * @param localSegment
     */
    public HazelcastCommandBusConnector(HazelcastCluster hazelcastCluster,CommandBus localSegment) {
        m_hazelcastCluster = hazelcastCluster;
        m_localSegment = localSegment;
        m_supportedCommands = Sets.newCopyOnWriteArraySet();
        m_nodeId = null;
        m_loadFactor = -1;
        m_queueListener = null;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param nodeId
     */
    public void setNodeId(String nodeId) {
        m_nodeId = nodeId;
    }

    /**
     *
     * @param loadFactor
     */
    public void setLoadFactor(int loadFactor) {
        m_loadFactor = loadFactor;
    }

    /**
     *
     */
    public void connect() {
        BlockingDeque<?> queue = m_hazelcastCluster.join(m_nodeId,m_loadFactor);
        if(queue != null && m_queueListener == null) {
            m_queueListener = new HazelcastCommandListener(queue);
            m_queueListener.run();
        }
    }

    /**
     *
     */
    public void disconenct() {
        if(m_hazelcastCluster != null) {
            m_queueListener.shutdown();
            try {
                m_queueListener.join(1000 * 5);
            } catch (InterruptedException e) {
                LOGGER.warn("Exception",e);
            } finally {
                m_queueListener = null;
            }
        }

        m_hazelcastCluster.leave(m_nodeId);
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void send(String routingKey, CommandMessage<?> command) throws Exception {
        send(routingKey,command,null);
    }

    @Override
    public <R> void send(String routingKey, CommandMessage<?> command, CommandCallback<R> callback) throws Exception {
        String destination = m_hazelcastCluster.getCommandDestination(routingKey, command);

        try {
            m_hazelcastCluster.send(destination,command);
            if(callback != null) {
                //TODO: do something
            }
        } catch(Exception e) {
            LOGGER.warn("Exception,e");
            throw e;
        }
    }

    @Override
    public <C> void subscribe(String commandName, CommandHandler<? super C> handler) {
        m_localSegment.subscribe(commandName, handler);
        if (m_supportedCommands.add(commandName)) {
            m_hazelcastCluster.registerCommandHandler(commandName,m_nodeId);
        }
    }

    @Override
    public <C> boolean unsubscribe(String commandName, CommandHandler<? super C> handler) {
        if (m_localSegment.unsubscribe(commandName, handler)) {
            m_supportedCommands.remove(commandName);
            m_hazelcastCluster.unregisterCommandHandler(commandName,m_nodeId);
            return true;
        }

        return false;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     */
    private class HazelcastCommandListener extends Thread {

        private final BlockingDeque<?> m_queue;
        private final AtomicBoolean m_running;

        /**
         * c-tor
         *
         * @param queue
         */
        public HazelcastCommandListener(BlockingDeque<?> queue) {
            m_queue = queue;
            m_running = new AtomicBoolean(true);
        }

        /**
         *
         */
        public void shutdown() {
            m_running.set(false);
        }

        @Override
        public void run() {
            while(m_running.get()) {
                try {
                    m_queue.poll(1, TimeUnit.SECONDS);
                }
                catch (InterruptedException e) {
                    LOGGER.warn("Exception",e);
                }
            }
        }
    }
}
