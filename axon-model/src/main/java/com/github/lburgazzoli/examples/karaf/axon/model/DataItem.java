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
package com.github.lburgazzoli.examples.karaf.axon.model;

import com.github.lburgazzoli.examples.karaf.axon.model.commands.DataItemCreateCommand;
import com.github.lburgazzoli.examples.karaf.axon.model.commands.DataItemUpdateCommand;
import com.github.lburgazzoli.examples.karaf.axon.model.events.DataItemCreatedEvent;
import com.github.lburgazzoli.examples.karaf.axon.model.events.DataItemUpdatedEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.serializer.Revision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 *
 */
@Revision("1")
public class DataItem extends AbstractAnnotatedAggregateRoot implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataItem.class);

    @AggregateIdentifier
    private String m_id;
    private String m_text;

    /**
     * c-tor
     */
    public DataItem() {
        m_id  = null;
        m_text = null;
    }

    /**
     * c-tor
     *
     * @param command
     */
    @CommandHandler
    public DataItem(DataItemCreateCommand command) {
        apply(new DataItemCreatedEvent(command.getId(),command.getText()));
    }

    // *************************************************************************
    //
    // *************************************************************************

    @CommandHandler
    public void handleDataItemUpdateCommand(DataItemUpdateCommand command) {
        apply(new DataItemUpdatedEvent(command.getId(),command.getText()));
    }

    // *************************************************************************
    //
    // *************************************************************************

    @EventHandler
    protected void handleDataItemCreatedEvent(DataItemCreatedEvent event) {
        LOGGER.debug(">>>> handleDataItemCreatedEvent : {}/{}/{},{}",
            this,m_id,event.getId(),event.getText());

        m_id   = event.getId();
        m_text = event.getText();
    }

    @EventHandler
    protected void handleDataItemUpdateCommand(DataItemUpdatedEvent event) {
        LOGGER.debug(">>>> handleDataItemUpdateCommand : {}/{}/{}/{}",
            this,m_id,event.getId(),event.getText());

        m_text = event.getText();
    }
}
