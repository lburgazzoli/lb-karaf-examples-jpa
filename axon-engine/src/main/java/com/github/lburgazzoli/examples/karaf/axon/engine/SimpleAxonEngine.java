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
package com.github.lburgazzoli.examples.karaf.axon.engine;

import com.github.lburgazzoli.examples.axon.IAxonEngine;
import com.github.lburgazzoli.examples.axon.cache.ICacheManager;
import com.github.lburgazzoli.examples.axon.helper.AxonCachingEventSourcingRepository;
import com.github.lburgazzoli.examples.axon.helper.AxonEventSourcingRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.Subscribable;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventListener;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventsourcing.EventSourcedAggregateRoot;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class SimpleAxonEngine implements IAxonEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleAxonEngine.class);

    private CommandBus m_commandBus;
    private CommandGateway m_commandGateway;
    private EventStore m_eventStore;
    private EventBus m_eventBus;
    private ICacheManager m_cacheManager;

    private final Set<EventListener> m_eventListeners;
    private final Map<Object,Subscribable> m_eventHandlers;
    private final Map<Class<? extends EventSourcedAggregateRoot>,AggregateSubscription> m_aggregates;

    /**
     * c-tor
     */
    public SimpleAxonEngine() {
        m_commandBus = null;
        m_commandGateway = null;
        m_eventStore = null;
        m_eventBus = null;
        m_cacheManager = null;
        m_eventListeners = Sets.newHashSet();
        m_eventHandlers = Maps.newConcurrentMap();
        m_aggregates = Maps.newConcurrentMap();
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     */
    public void init() {
    }

    /**
     *
     */
    public void destroy() {
        for(EventListener listener : m_eventListeners) {
            m_eventBus.unsubscribe(listener);
        }

        m_eventListeners.clear();

        for(Subscribable subscription : m_eventHandlers.values()) {
            subscription.unsubscribe();
        }

        m_eventHandlers.clear();

        for(AggregateSubscription subscription : m_aggregates.values()) {
            subscription.handler.unsubscribe();
        }

        m_aggregates.clear();
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param cacheManager
     */
    public void setCacheManager(ICacheManager cacheManager) {
        m_cacheManager = cacheManager;
    }

    /**
     *
     * @param commandGateway
     */
    public void setCommandGateway(CommandGateway commandGateway) {
        m_commandGateway = commandGateway;
    }

    /**
     *
     * @param commandBus
     */
    public void setCommandBus(CommandBus commandBus) {
        m_commandBus = commandBus;
    }

    /**
     *
     * @param eventBus
     */
    public void setEventBus(EventBus eventBus) {
        m_eventBus = eventBus;
    }

    /**
     *
     * @param eventStore
     */
    public void setEventStore(EventStore eventStore) {
        m_eventStore = eventStore;
    }

    /**
     *
     * @return
     */
    @Override
    public EventStore getEventStore() {
        return m_eventStore;
    }

    /**
     *
     * @return
     */
    @Override
    public EventBus getEventBus() {
        return m_eventBus;
    }

    /**
     *
     * @return
     */
    @Override
    public CommandBus getCommandBus() {
        return m_commandBus;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<EventSourcingRepository<?>> getRepositories() {
        List<EventSourcingRepository<?>> repositories = Lists.newArrayListWithCapacity(m_aggregates.size());
        for(AggregateSubscription subscription : m_aggregates.values()) {
            repositories.add(subscription.repository);
        }

        return repositories;
    }


    // *************************************************************************
    //
    // *************************************************************************


    /**
     *
     * @param eventHandler
     */
    public void addEventHandler(Object eventHandler) {
        if(!m_eventHandlers.containsKey(eventHandler)) {
            m_eventHandlers.put(
                eventHandler,
                AnnotationEventListenerAdapter.subscribe(eventHandler,m_eventBus));
        }

    }

    /**
     *
     * @param eventHandler
     */
    public void removeEventHandler(Object eventHandler) {
        if(m_eventHandlers.containsKey(eventHandler)) {
            m_eventHandlers.get(eventHandler).unsubscribe();
            m_eventHandlers.remove(eventHandler);
        }
    }

    /**
     *
     * @param eventListener
     */
    @Override
    public void addEventListener(EventListener eventListener) {
        if(m_eventListeners.add(eventListener)) {
            m_eventBus.subscribe(eventListener);
        }
    }

    /**
     *
     * @param eventListener
     */
    @Override
    public void removeEventListener(EventListener eventListener) {
        if(eventListener != null) {
            m_eventBus.unsubscribe(eventListener);
        }
    }

    /**
     *
     * @param aggregateType
     */
    @Override
    public void addAggregateType(Class<? extends EventSourcedAggregateRoot> aggregateType) {
        removeAggregateType(aggregateType);

        EventSourcingRepository eventRepository = null;
        if(m_cacheManager == null) {
            eventRepository = new AxonEventSourcingRepository(aggregateType,this);
        } else {
            eventRepository = new AxonCachingEventSourcingRepository(aggregateType,this,m_cacheManager);
        }

        m_aggregates.put(aggregateType,new AggregateSubscription(
            eventRepository,
            AggregateAnnotationCommandHandler.subscribe(
                aggregateType,
                eventRepository,
                m_commandBus)));
    }

    /**
     *
     * @param aggregateType
     */
    @Override
    public void removeAggregateType(Class<? extends EventSourcedAggregateRoot> aggregateType) {
        if(m_aggregates.containsKey(aggregateType)) {
            m_aggregates.get(aggregateType).handler.unsubscribe();
            m_aggregates.remove(aggregateType);
        }
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void send(Object command) {
        m_commandGateway.send(command);
    }

    @Override
    public <R> void send(Object command, CommandCallback<R> callback) {
        m_commandGateway.send(command,callback);
    }

    @Override
    public <R> R sendAndWait(Object command) {
        return m_commandGateway.sendAndWait(command);
    }

    @Override
    public <R> R sendAndWait(Object command, long timeout, TimeUnit unit) {
        return m_commandGateway.sendAndWait(command,timeout,unit);
    }

    // *************************************************************************
    //
    // *************************************************************************

    private final class AggregateSubscription {

        public final EventSourcingRepository repository;
        public final Subscribable handler;

        /**
         * c-tor
         *
         * @param repository
         * @param handler
         */
        public AggregateSubscription(EventSourcingRepository repository,Subscribable handler) {
            this.repository = repository;
            this.handler    = handler;
        }
    }
}
