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
package com.github.lburgazzoli.examples.axon.store;

import com.github.lburgazzoli.Utils;
import com.github.lburgazzoli.osgi.hazelcast.IHazelcastManager;
import com.hazelcast.core.IList;
import org.apache.commons.lang3.CharEncoding;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.domain.SimpleDomainEventStream;
import org.axonframework.eventstore.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 */
public class HazelcastEventStore implements EventStore {
    private static final Logger LOGGER = LoggerFactory.getLogger(HazelcastEventStore.class);

    private final IHazelcastManager m_hazelcastManager;

    /**
     * c-tor
     *
     * @param hazelcastManager
     */
    public HazelcastEventStore(IHazelcastManager hazelcastManager) {
        m_hazelcastManager = hazelcastManager;
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void appendEvents(String type, DomainEventStream events) {
        IList<DomainEventMessage> eventStore = null;
        while(events.hasNext()) {
            DomainEventMessage dem = events.next();
            if(dem.getSequenceNumber() == 0) {
                eventStore = m_hazelcastManager.getList(
                    getListIdentifier(type,dem.getAggregateIdentifier()));
            }

            if(eventStore != null) {
                eventStore.add(dem);
            }
        }
    }

    @Override
    public DomainEventStream readEvents(String type, Object identifier) {
        IList<DomainEventMessage> eventStore =
            m_hazelcastManager.getList(getListIdentifier(type, identifier));

        // Workaround for HZ serialization issues
        ClassLoader classLoader = Utils.swapContextClassLoader(m_hazelcastManager.getClassloader());
        SimpleDomainEventStream sdes = new SimpleDomainEventStream(eventStore);
        Utils.swapContextClassLoader(classLoader);

        return sdes;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param type
     * @param identifier
     * @return
     */
    private String getListIdentifier(String type,Object identifier) {
        return String.format("%s_%s",
            type,
            safeIdentifier(identifier));
    }


    /**
     *
     * @param id
     * @return
     */
    private String safeIdentifier(Object id) {
        try {
            return URLEncoder.encode(id.toString(),CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesnt support UTF-8?", e);
        }
    }
}
