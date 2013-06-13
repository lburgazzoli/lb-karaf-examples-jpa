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
package com.github.lburgazzoli.examples.axon.helper;

import com.github.lburgazzoli.examples.axon.IAxonEngine;
import com.github.lburgazzoli.examples.axon.cache.ICacheManager;
import org.axonframework.eventsourcing.CachingEventSourcingRepository;
import org.axonframework.eventsourcing.EventSourcedAggregateRoot;
import org.axonframework.eventsourcing.GenericAggregateFactory;

/**
 *
 */
public class AxonCachingEventSourcingRepository<T extends EventSourcedAggregateRoot> extends CachingEventSourcingRepository<T> {
    /**
     * c-tor
     *
     * @param aggregateType
     * @param engine
     */
    public AxonCachingEventSourcingRepository(
        final Class<T> aggregateType, final IAxonEngine engine, final ICacheManager cacheManager) {
        super(new GenericAggregateFactory<T>(aggregateType),engine.getEventStore());
        super.setEventBus(engine.getEventBus());
        super.setCache(cacheManager.getCache(aggregateType));
    }
}
