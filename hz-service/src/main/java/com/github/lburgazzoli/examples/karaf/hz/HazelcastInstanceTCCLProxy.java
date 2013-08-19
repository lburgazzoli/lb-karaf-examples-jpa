/**
 *
 * Copyright 2013 lb
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.examples.karaf.hz;

import com.hazelcast.core.IList;
import com.hazelcast.core.ILock;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ISet;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.MultiMap;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class HazelcastInstanceTCCLProxy extends AbstractHazelcastInstanceProxy {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(HazelcastInstanceTCCLProxy.class);

    private final ClassLoader m_classLoader;

    /**
     * c-tor
     *
     * @param bundleContext
     * @param instanceProvider
     * @param objectPrefix
     */
    public HazelcastInstanceTCCLProxy(
        BundleContext bundleContext, IHazelcastInstanceProvider instanceProvider, String objectPrefix) {
        this(bundleContext,instanceProvider,HazelcastInstanceTCCLProxy.class.getClassLoader(),objectPrefix);
    }

    /**
     * c-tor
     *
     * @param bundleContext
     * @param instanceProvider
     * @param classLoader
     * @param objectPrefix
     */
    public HazelcastInstanceTCCLProxy(
        BundleContext bundleContext, IHazelcastInstanceProvider instanceProvider, ClassLoader classLoader, String objectPrefix) {
        super(bundleContext,instanceProvider,objectPrefix);

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

    @Override
    public <T> ISet<T> getSet(String name) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        ISet<T> rv = null;

        if(hasInstance()) {
            try {
                Thread.currentThread().setContextClassLoader(getClassloader());
                rv = getInstance().getSet(getDistributedObjectName(name));
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
    public ILock getLock(Object name) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        ILock rv = null;

        if(hasInstance()) {
            try {
                Thread.currentThread().setContextClassLoader(getClassloader());
                rv = getInstance().getLock(getDistributedObjectName(name.toString()));
            } finally {
                Thread.currentThread().setContextClassLoader(cl);
            }
        }

        return  rv;
    }
}
