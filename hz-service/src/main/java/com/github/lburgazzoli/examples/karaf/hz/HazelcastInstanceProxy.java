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
public class HazelcastInstanceProxy extends AbstractHazelcastInstanceProxy {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(HazelcastInstanceProxy.class);

    /**
     * c-tor
     *
     * @param bundleContext
     * @param instanceProvider
     * @param objectPrefix
     */
    public HazelcastInstanceProxy(
        BundleContext bundleContext, IHazelcastInstanceProvider instanceProvider, String objectPrefix) {
        super(bundleContext,instanceProvider,objectPrefix);
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public ClassLoader getClassloader() {
        return hasInstance() ? getInstance().getConfig().getClassLoader() : null;
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public <K,V> IMap<K,V> getMap(String name) {
        IMap<K,V> rv = null;
        if(hasInstance()) {
            rv = getInstance().getMap(getDistributedObjectName(name));
        }

        return rv;
    }

    @Override
    public <K,V> MultiMap<K,V> getMultiMap(String name) {
        MultiMap<K,V> rv = null;
        if(hasInstance()) {
            rv = getInstance().getMultiMap(getDistributedObjectName(name));
        }

        return rv;
    }


    @Override
    public <T> IList<T> getList(String name) {
        IList<T> rv = null;
        if(hasInstance()) {
            rv = getInstance().getList(getDistributedObjectName(name));
        }

        return rv;
    }

    public <T> IQueue<T> getQueue(String name) {
        IQueue<T> rv = null;
        if(hasInstance()) {
            rv = getInstance().getQueue(getDistributedObjectName(name));
        }

        return rv;
    }

    @Override
    public <T> ITopic<T> getTopic(String name) {
        ITopic<T> rv = null;
        if(hasInstance()) {
            rv = getInstance().getTopic(getDistributedObjectName(name));
        }

        return rv;
    }

    @Override
    public <T> ISet<T> getSet(String name) {
        ISet<T> rv = null;
        if(hasInstance()) {
            rv = getInstance().getSet(getDistributedObjectName(name));
        }

        return rv;
    }

    @Override
    public ILock getLock(Object name) {
        ILock rv = null;
        if(hasInstance()) {
            rv = getInstance().getLock(getDistributedObjectName(name.toString()));
        }

        return rv;
    }
}
