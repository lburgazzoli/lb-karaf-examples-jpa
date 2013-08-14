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
package com.github.lburgazzoli.osgi.hazelcast;

import com.github.lburgazzoli.osgi.BundleContextAware;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 */
public abstract class AbstractHazelcastInstance extends BundleContextAware implements IHazelcastInstanceProxy {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(AbstractHazelcastInstance.class);

    private HazelcastInstance m_instance;
    private Config m_config;
    private AtomicBoolean m_active;
    private String m_itemPrefix;

    /**
     * c-tor
     *
     * @param bundleContext
     * @param config
     */
    public AbstractHazelcastInstance(BundleContext bundleContext, Config config) {
        super(bundleContext);

        m_active = new AtomicBoolean(false);
        m_config = config;
        m_itemPrefix = null;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param itemPrefix
     */
    public void setDistributedObjectNamePrefix(String itemPrefix) {
        m_itemPrefix = itemPrefix;
    }

    /**
     *
     * @param name
     * @return
     */
    protected String getDistributedObjectName(String name) {
        return StringUtils.isEmpty(m_itemPrefix) ? name : m_itemPrefix + "." + name;
    }

    // *************************************************************************
    //
    // *************************************************************************

    public void init() {
        if(m_instance == null) {
            LOGGER.debug("Initialize Instance");

            m_instance = Hazelcast.newHazelcastInstance(m_config);

            LOGGER.debug("New Instance created     : {}", m_instance);
            LOGGER.debug("New Instance ClassLoader : {}", m_instance.getConfig().getClassLoader());

            m_active.set(true);
        }
    }

    public void destroy() {
        if(m_instance != null) {
            m_active.set(false);

            LOGGER.debug("Destroy instance {}", m_instance);

            m_instance.getLifecycleService().shutdown();
            m_instance = null;
        }
    }

    // *************************************************************************
    // IHazelcastInstanceProxy
    // *************************************************************************

    @Override
    public String getId() {
        return hasInstance() ? m_instance.getCluster().getLocalMember().getUuid() : null;
    }

    @Override
    public HazelcastInstance getInstance() {
        return m_instance;
    }

    @Override
    public boolean hasInstance() {
        return m_instance != null;
    }
}
