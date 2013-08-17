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
package com.github.lburgazzoli.examples.karaf.hz.data.listener;

import com.github.lburgazzoli.examples.karaf.hz.IHazelcastInstanceProvider;
import com.github.lburgazzoli.examples.karaf.hz.data.HzData;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 *
 */
public class HzDataListener implements MessageListener<HzData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HzDataListener.class);

    private final IHazelcastInstanceProvider m_instanceProvider;
    private String m_listenerKey;
    private ITopic<HzData> m_topic;

    /**
     * c-tor
     */
    public HzDataListener(IHazelcastInstanceProvider instanceProvider) {
        m_instanceProvider = instanceProvider;
        m_listenerKey      = null;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     */
    public void init() {
        m_topic       = m_instanceProvider.getInstance().getTopic("hztest:topic");
        m_listenerKey = m_topic.addMessageListener(this);
    }

    /**
     *
     */
    public void destroy() {
        if((m_topic != null) && StringUtils.isNotBlank(m_listenerKey)) {
            m_topic.removeMessageListener(m_listenerKey);
        }
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void onMessage(Message<HzData> message) {
        LOGGER.info("Received message from member <{}>, at <{}>, value <{}>",
            message.getPublishingMember().getUuid(),
            new Date(message.getPublishTime()),
            message.getMessageObject()
        );
    }
}
