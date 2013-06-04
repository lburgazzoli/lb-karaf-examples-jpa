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
import com.github.lburgazzoli.examples.axon.serializer.JacksonObjectMapper;
import com.github.lburgazzoli.osgi.hazelcast.IHazelcastManager;
import com.google.common.collect.Maps;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.domain.SimpleDomainEventStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

/**
 *
 */
public class HazelcastEventStore implements IHazelcastEventStore {
    private static final Logger LOGGER = LoggerFactory.getLogger(HazelcastEventStore.class);

    private final IHazelcastManager m_hazelcastManager;
    private final Map<String,HazelcastDomainEventStore> m_domainEventStore;
    private final JacksonObjectMapper m_objectMapper;

    /**
     * c-tor
     *
     * @param hazelcastManager
     */
    public HazelcastEventStore(IHazelcastManager hazelcastManager) {
        m_hazelcastManager = hazelcastManager;
        m_domainEventStore = Maps.newHashMap();
        m_objectMapper = new JacksonObjectMapper();
    }

    // *************************************************************************
    // IHazelcastEventStore
    // *************************************************************************

    @Override
    public HazelcastDomainEventStore getDomainEventStore(String eventStoreId) {
        return m_domainEventStore.get(eventStoreId);
    }

    @Override
    public Collection<HazelcastDomainEventStore> getDomainEventStores() {
        return m_domainEventStore.values();
    }

    // *************************************************************************
    // EventStore
    // *************************************************************************

    @Override
    public void appendEvents(String type, DomainEventStream events) {
        int size = 0;

        String listId = null;
        HazelcastDomainEventStore hdes = null;

        while(events.hasNext()) {
            DomainEventMessage dem = events.next();
            if(size == 0) {
                listId = HazelcastStorageUtils.getStorageIdentifier(type, dem);
                hdes   = m_domainEventStore.get(listId);

                if(hdes == null) {
                    hdes = new HazelcastDomainEventStore(
                        type,dem.getAggregateIdentifier().toString(),m_hazelcastManager);

                    m_domainEventStore.put(hdes.getStorageId(),hdes);
                }

                if(dem.getSequenceNumber() == 0) {
                    hdes.clear();
                }
            }

            if(hdes != null) {
                LOGGER.debug("Add : <{}>",dem.getPayload().toString());
                hdes.add(dem);
                size++;
            }
        }

        LOGGER.debug("appendEvents: type={}, nbStoredEvents={}, eventStoreSize={}",
            type,size,(hdes != null) ? hdes.getStorageSize() : 0);
    }

    @Override
    public DomainEventStream readEvents(String type, Object identifier) {
        String listId = HazelcastStorageUtils.getStorageIdentifier(type,identifier.toString());
        HazelcastDomainEventStore hdes = m_domainEventStore.get(listId);

        if(hdes != null) {
            // Workaround for HZ serialization issues
            ClassLoader classLoader =
                Utils.swapContextClassLoader(m_hazelcastManager.getClassloader());

            DomainEventStream des = hdes.getEventStream();
            Utils.swapContextClassLoader(classLoader);

            LOGGER.debug("readEvents: type={}, nbStoredEvents={}",type,hdes.getStorageSize());

            return des;
        }

        return new SimpleDomainEventStream();
    }
}
