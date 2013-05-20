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
