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
package com.github.lburgazzoli.examples.axon.hazelcast.store;

import com.github.lburgazzoli.Utils;
import com.github.lburgazzoli.osgi.hazelcast.IHazelcastManager;
import com.google.common.collect.Lists;
import com.hazelcast.core.IList;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.domain.SimpleDomainEventStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *
 */
public class HazelcastDomainEventStore {
    private static final Logger LOGGER = LoggerFactory.getLogger(HazelcastDomainEventStore.class);

    private final String m_aggregateType;
    private final String m_aggregateId;
    private final String m_storageId;
    private final IHazelcastManager m_hazelcastManager;
    private final IList<HazelcastDomainEventMessage> m_storage;

    /**
     *
     * @param aggregateType
     * @param aggregateId
     * @param hazelcastManager
     */
    public HazelcastDomainEventStore(String aggregateType, String aggregateId, IHazelcastManager hazelcastManager) {
        m_aggregateType = aggregateType;
        m_aggregateId = aggregateId;
        m_storageId = HazelcastStorageUtils.getStorageIdentifier(m_aggregateType, m_aggregateId);
        m_hazelcastManager = hazelcastManager;
        m_storage = m_hazelcastManager.getList(m_storageId);
    }

    /**
     *
     */
    public void clear() {
        ClassLoader cl = null;
        try {
            cl = Utils.swapContextClassLoader(getClassLoader());
            m_storage.clear();
        } finally {
            Utils.swapContextClassLoader(cl);
        }
    }

    /**
     *
     * @return
     */
    public ClassLoader getClassLoader() {
        return m_hazelcastManager.getClassloader();
    }

    /**
     *
     * @return
     */
    public String getAggregateType() {
        return m_aggregateType;
    }

    /**
     *
     * @return
     */
    public String getAggregateId() {
        return m_aggregateId;
    }

    /**
     *
     * @return
     */
    public String getStorageId() {
        return m_storageId;
    }

    /**
     *
     * @return
     */
    public IList<HazelcastDomainEventMessage> getStorage() {
        return m_storage;
    }

    /**
     *
     * @return
     */
    public int getStorageSize() {
        return m_storage.size();
    }

    /**
     *
     * @param message
     */
    public void add(DomainEventMessage message) {
        m_storage.add(new HazelcastDomainEventMessage(message));
    }

    /**
     *
     * @return
     */
    public DomainEventStream getEventStream() {
        DomainEventStream des = null;
        ClassLoader cl = null;

        try {
            cl = Utils.swapContextClassLoader(getClassLoader());

            List<DomainEventMessage> messages =
                Lists.newArrayListWithCapacity(m_storage.size());

            des = new SimpleDomainEventStream(m_storage);
        } finally {
            Utils.swapContextClassLoader(cl);
        }

        return des;
    }
}
