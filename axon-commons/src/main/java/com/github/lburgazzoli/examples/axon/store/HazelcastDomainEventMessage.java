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

import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.MetaData;
import org.joda.time.DateTime;

import java.util.Map;

/**
 *
 */
public class HazelcastDomainEventMessage<T> implements DomainEventMessage<T> {

    /**
     * c-tor
     */
    public HazelcastDomainEventMessage() {
    }

    @Override
    public long getSequenceNumber() {
        return 0;
    }

    @Override
    public Object getAggregateIdentifier() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public MetaData getMetaData() {
        return null;
    }

    @Override
    public T getPayload() {
        return null;
    }

    @Override
    public Class getPayloadType() {
        return null;
    }

    @Override
    public DateTime getTimestamp() {
        return null;
    }

    @Override
    public DomainEventMessage withMetaData(Map<String, ?> metaData) {
        return null;
    }

    @Override
    public DomainEventMessage andMetaData(Map<String, ?> metaData) {
        return null;
    }
}
