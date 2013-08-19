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
package com.github.lburgazzoli.examples.karaf.axon.model;

import com.github.lburgazzoli.examples.axon.IAxonEngine;
import com.github.lburgazzoli.examples.axon.IAxonModelService;
import com.github.lburgazzoli.examples.karaf.axon.model.events.DataItemCreatedEvent;
import com.github.lburgazzoli.examples.karaf.axon.model.events.DataItemUpdatedEvent;
import com.github.lburgazzoli.osgi.IOSGiLifeCycle;
import org.axonframework.common.Subscribable;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class DataItemService implements IAxonModelService,IOSGiLifeCycle {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataItemService.class);


    private final IAxonEngine m_axonEngine;
    private final Object m_eventListener;
    private EventSourcingRepository m_eventRepository;
    private Subscribable m_subscriptionHandler;

    /**
     * c-tor
     *
     * @param axonEngine
     */
    public DataItemService(IAxonEngine axonEngine) {
        m_axonEngine = axonEngine;
        m_eventListener = new DataItemEventHandler();
    }

    @Override
    public void init() {
        m_axonEngine.addEventHandler(m_eventListener);
        m_axonEngine.addAggregateType(DataItem.class);
    }

    @Override
    public void destroy() {
        m_axonEngine.removeEventHandler(m_eventListener);
        m_axonEngine.removeAggregateType(DataItem.class);
    }

    // *************************************************************************
    //
    // *************************************************************************

    private final class DataItemEventHandler {
        @EventHandler
        public void handle(DataItemCreatedEvent data) {
            LOGGER.debug("handleDataCreatedEvent <{}> : id={}, text={}",
                data.getClass().getName(),
                data.getId(),
                data.getText());
        }

        @EventHandler
        public void handle(DataItemUpdatedEvent data) {
            LOGGER.debug("handleDataUpdatedEvent <{}> : id={}, text={}",
                    data.getClass().getName(),
                    data.getId(),
                    data.getText());
        }
    }
}
