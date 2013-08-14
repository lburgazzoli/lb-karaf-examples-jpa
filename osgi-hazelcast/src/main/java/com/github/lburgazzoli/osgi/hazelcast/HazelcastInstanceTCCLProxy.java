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

import com.hazelcast.config.Config;
import com.hazelcast.core.IList;
import com.hazelcast.core.ILock;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.MultiMap;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class HazelcastInstanceTCCLProxy extends AbstractHazelcastInstance {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(HazelcastInstanceTCCLProxy.class);

    private final ClassLoader m_classLoader;

    /**
     * c-tor
     *
     * @param bundleContext
     * @param config
     */
    public HazelcastInstanceTCCLProxy(BundleContext bundleContext, Config config) {
        this(bundleContext,config,HazelcastInstanceTCCLProxy.class.getClassLoader());
    }

    /**
     * c-tor
     *
     * @param bundleContext
     * @param config
     * @param classLoader
     */
    public HazelcastInstanceTCCLProxy(BundleContext bundleContext, Config config, ClassLoader classLoader) {
        super(bundleContext,config);

        m_classLoader = classLoader;
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public ClassLoader getClassloader() {
        return m_classLoader;
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public <K,V> IMap<K,V> getMap(String name) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        IMap<K,V> rv = null;

        if(hasInstance()) {
            try {
                Thread.currentThread().setContextClassLoader(getClassloader());
                rv = getInstance().getMap(getDistributedObjectName(name));
            } finally {
                Thread.currentThread().setContextClassLoader(cl);
            }
        }

        return  rv;
    }

    @Override
    public <K,V> MultiMap<K,V> getMultiMap(String name) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        MultiMap<K,V> rv = null;

        if(hasInstance()) {
            try {
                Thread.currentThread().setContextClassLoader(getClassloader());
                rv = getInstance().getMultiMap(getDistributedObjectName(name));
            } finally {
                Thread.currentThread().setContextClassLoader(cl);
            }
        }

        return  rv;
    }

    @Override
    public <T> IList<T> getList(String name) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        IList<T> rv = null;

        if(hasInstance()) {
            try {
                Thread.currentThread().setContextClassLoader(getClassloader());
                rv = getInstance().getList(getDistributedObjectName(name));
            } finally {
                Thread.currentThread().setContextClassLoader(cl);
            }
        }

        return  rv;
    }

    public <T> IQueue<T> getQueue(String name) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        IQueue<T> rv = null;

        if(hasInstance()) {
            try {
                Thread.currentThread().setContextClassLoader(getClassloader());
                rv = getInstance().getQueue(getDistributedObjectName(name));
            } finally {
                Thread.currentThread().setContextClassLoader(cl);
            }
        }

        return  rv;
    }

    @Override
    public <T> ITopic<T> getTopic(String name) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        ITopic<T> rv = null;

        if(hasInstance()) {
            try {
                Thread.currentThread().setContextClassLoader(getClassloader());
                rv = getInstance().getTopic(getDistributedObjectName(name));
            } finally {
                Thread.currentThread().setContextClassLoader(cl);
            }
        }

        return  rv;
    }

    @Override
    public ILock getLock(String name) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        ILock rv = null;

        if(hasInstance()) {
            try {
                Thread.currentThread().setContextClassLoader(getClassloader());
                rv = getInstance().getLock(getDistributedObjectName(name));
            } finally {
                Thread.currentThread().setContextClassLoader(cl);
            }
        }

        return  rv;
    }
}
