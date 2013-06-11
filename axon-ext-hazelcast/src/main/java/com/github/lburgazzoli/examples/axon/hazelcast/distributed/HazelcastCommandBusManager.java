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

import com.github.lburgazzoli.osgi.hazelcast.IHazelcastManager;
import com.hazelcast.core.MultiMap;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

/**
 *
 */
public class HazelcastCommandBusManager {
    private final static Logger LOGEGR = LoggerFactory.getLogger(HazelcastCommandBusManager.class);

    private final static String REG_CMD_HANDLERS      = "reg.cmd.handlers";
    private final static String REG_CMD_DESTINATIONS  = "reg.cmd.destinations";
    private final static String REG_NODES             = "reg.nodes";
    private final static String ATTR_HZ_CLUSTER_ID    = "hz.cluster.id";
    private final static String ATTR_CONNECTOR_ID     = "conenctor.id";

    private final IHazelcastManager m_hazelcastManager;
    private final Map<String,Map<String,Object>> m_clusterRegistry;
    private final Map<String,String> m_cmdDestinations;

    /**
     * c-tor
     *
     * @param hazelcastManager
     */
    public HazelcastCommandBusManager(IHazelcastManager hazelcastManager) {
        m_hazelcastManager = hazelcastManager;
        m_clusterRegistry  = m_hazelcastManager.getMap(REG_NODES);
        m_cmdDestinations  = m_hazelcastManager.getMap(REG_CMD_DESTINATIONS);
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param id
     */
    public BlockingDeque<?> join(final String id) {
        if(StringUtils.isNotBlank(id)) {
            if(!m_clusterRegistry.containsKey(id)) {
                m_clusterRegistry.put(id,new HashMap<String,Object>() {{
                    put(ATTR_HZ_CLUSTER_ID,m_hazelcastManager.getId());
                    put(ATTR_CONNECTOR_ID ,id);
                }});

                return (BlockingDeque<?>)m_hazelcastManager.getQueue(id);
            } else {
                LOGEGR.warn("Service {} already registered",id);
            }
        } else {
            LOGEGR.warn("Service does not declare an ID");
        }

        return null;
    }

    /**
     *
     * @param id
     */
    public void leave(String id) {
        m_clusterRegistry.remove(id);
    }

    /**
     *
     * @param commandName
     * @param id
     */
    public void registerCommandHandler(String commandName,String id) {
        m_hazelcastManager.getMultiMap(REG_CMD_HANDLERS).put(commandName, id);
    }

    /**
     *
     * @param commandName
     * @param id
     */
    public void unregisterCommandHandler(String commandName,String id) {
        m_hazelcastManager.getMultiMap(REG_CMD_HANDLERS).remove(commandName,id);
    }

    /**
     *
     * @param command
     * @return
     */
    public String getHandlerForCommand(CommandMessage<?> command) {
        MultiMap<String,String> map   = m_hazelcastManager.getMultiMap(REG_CMD_HANDLERS);
        String[]                items = map.get(command.getCommandName()).toArray(new String[]{});

        if(items.length == 1) {
            return items[0];
        } else if(items.length > 1) {
            return items[new Random().nextInt(items.length)];
        }

        return null;
    }

    /**
     *
     * @param destination
     * @param command
     * @throws InterruptedException
     */
    public void send(String destination,CommandMessage<?> command) throws InterruptedException {
        m_hazelcastManager.getQueue(destination).put(command);
    }

    /**
     *
     * @param routingKey
     * @param command
     * @return
     */
    public String getCommandDestination(String routingKey, CommandMessage<?> command) {
        String destination = m_cmdDestinations.get(routingKey);
        if(StringUtils.isBlank(destination)) {
            destination = getHandlerForCommand(command);
            if(m_clusterRegistry.containsKey(destination)) {
                m_cmdDestinations.put(routingKey,destination);
            }
        }

        if(!m_clusterRegistry.containsKey(destination)) {
            destination = null;
        }

        return destination;
    }
}
