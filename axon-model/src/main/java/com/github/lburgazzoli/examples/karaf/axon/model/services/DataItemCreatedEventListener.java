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
package com.github.lburgazzoli.examples.karaf.axon.model.services;

import com.github.lburgazzoli.examples.karaf.axon.helper.AbstractEventListener;
import com.github.lburgazzoli.examples.karaf.axon.model.DataCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class DataItemCreatedEventListener extends AbstractEventListener<DataCreatedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataItemCreatedEventListener.class);

    /**
     * c-tor
     */
    public DataItemCreatedEventListener() {
        super(DataCreatedEvent.class);
    }

    @Override
    protected void doHandle(DataCreatedEvent data) {
        LOGGER.debug("Handle Event <{}> : id={}, text={}",
        data.getClass().getName(),
        data.getId(),
        data.getText());
    }
}
