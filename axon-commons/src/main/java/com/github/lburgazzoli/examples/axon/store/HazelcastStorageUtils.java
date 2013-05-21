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

import org.apache.commons.lang3.CharEncoding;
import org.axonframework.domain.DomainEventMessage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 */
public class HazelcastStorageUtils {

    /**
     *
     * @param type
     * @param identifier
     * @return
     */
    public static String getStorageIdentifier(String type,String identifier) {
        return String.format("%s_%s",
            type,
            safeIdentifier(identifier));
    }

    /**
     *
     * @param type
     * @param message
     * @return
     */
    public static String getStorageIdentifier(String type, DomainEventMessage message) {
        return getStorageIdentifier(type, message.getAggregateIdentifier().toString());
    }

    /**
     *
     * @param id
     * @return
     */
    public static String safeIdentifier(String id) {
        try {
            return URLEncoder.encode(id,CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesnt support UTF-8?", e);
        }
    }
}
