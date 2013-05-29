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
package com.github.lburgazzoli.examples.axon.cache;

import com.github.lburgazzoli.osgi.hazelcast.IHazelcastManager;
import net.sf.jsr107cache.Cache;
import org.axonframework.eventsourcing.EventSourcedAggregateRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class HazelcastCacheManager implements ICacheManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(HazelcastCacheManager.class);

    private final IHazelcastManager m_hazelcastManager;

    /**
     *
     * @param hazelcastManager
     */
    public HazelcastCacheManager(IHazelcastManager hazelcastManager) {
        m_hazelcastManager = hazelcastManager;
    }

    @Override
    public Cache getCache(Class<? extends EventSourcedAggregateRoot> aggregateType) {
        String cacheName = "axon-cache-<" + aggregateType.getName() + ">";
        return new HazelcastCache(
            m_hazelcastManager.getClassloader(),
            m_hazelcastManager.getMap(cacheName));
    }
}
