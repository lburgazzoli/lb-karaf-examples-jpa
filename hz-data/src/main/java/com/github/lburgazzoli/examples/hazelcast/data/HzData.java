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
package com.github.lburgazzoli.examples.hazelcast.data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 *
 */
public class HzData implements Serializable {

    private String m_payload;

    /**
     * c-tor
     */
    public HzData() {
        this(null);
    }

    /**
     * c-tor
     *
     * @param payload
     */
    public HzData(final String payload) {
        m_payload = payload;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @return
     */
    public String getPayload() {
        return m_payload;
    }

    /**
     *
     * @param payload
     */
    public void setPayload(String payload) {
        m_payload = payload;
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("payload",m_payload)
            .toString();
    }
}
