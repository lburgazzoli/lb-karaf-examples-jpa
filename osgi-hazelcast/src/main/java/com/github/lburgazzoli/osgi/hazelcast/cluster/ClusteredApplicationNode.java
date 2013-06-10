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
package com.github.lburgazzoli.osgi.hazelcast.cluster;

import com.github.lburgazzoli.osgi.hazelcast.IHazelcastManager;
import com.hazelcast.core.ILock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.github.lburgazzoli.osgi.hazelcast.cluster.ClusteredApplicationConstants.CLUSTER_LOCK;
import static com.github.lburgazzoli.osgi.hazelcast.cluster.ClusteredApplicationConstants.NODE_HEARTBEAT;

/**
 *
 */
public class ClusteredApplicationNode implements IClusteredApplicationNode, Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClusteredApplicationNode.class);

    private final ScheduledExecutorService m_executorService;
    private final IHazelcastManager m_hzManager;
    private final String m_nodeId;

    private Map<String,Map<String,Object>> m_registry;

    /**
     *
     * @param hazelcastManager
     * @param nodeId
     */
    public ClusteredApplicationNode(IHazelcastManager hazelcastManager, String nodeId) {
        m_hzManager        = hazelcastManager;
        m_executorService  = Executors.newScheduledThreadPool(1);
        m_nodeId           = nodeId;
        m_registry         = null;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     */
    public void init() {
        if(m_registry == null) {
            ILock guard = m_hzManager.getLock(CLUSTER_LOCK);

            try {
                guard.lock();
            } finally {
                guard.unlock();
            }
        }
    }

    /**
     *
     */
    public void destroy() {
        m_executorService.shutdown();
        try {
            if (!m_executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                if (!m_executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    LOGGER.warn("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            m_executorService.shutdownNow();
        }
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void run() {
        setProperty(NODE_HEARTBEAT, new Date());
        LOGGER.debug("Update KA <{}>",getProperty(NODE_HEARTBEAT));
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param key
     * @param val
     */
    public void setProperty(String key,Object val) {
    }

    /**
     *
     * @param key
     * @return
     */
    public Object getProperty(String key) {
        Object rv = null;
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        try {
            Thread.currentThread().setContextClassLoader(m_hzManager.getClassloader());
        } finally {
            Thread.currentThread().setContextClassLoader(cl);
        }

        return rv;
    }
}
