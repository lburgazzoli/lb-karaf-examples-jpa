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
package com.github.lburgazzoli.serialization.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lburgazzoli.json.JacksonHelper;
import com.github.lburgazzoli.serialization.ITypeSerializer;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
public class JacksonTypeSerializer<T> implements ITypeSerializer<T> {
    private final ObjectMapper m_mapper;
    private final Class<T> m_type;

    /**
     * c-tor
     *
     * @param type
     */
    public JacksonTypeSerializer(Class<T> type) {
        this(JacksonHelper.DEFAULT_JSON,type);
    }

    /**
     * c-tor
     *
     * @param binary
     * @param type
     */
    public JacksonTypeSerializer(boolean binary,Class<T> type) {
        this(binary ? JacksonHelper.DEFAULT_BSON : JacksonHelper.DEFAULT_JSON,type);
    }

    /**
     * c-tor
     *
     * @param mapper
     * @param type
     */
    public JacksonTypeSerializer(ObjectMapper mapper, Class<T> type) {
        m_mapper = mapper;
        m_type   = type;
    }

    // *************************************************************************
    // ITypeSerializer<T>
    // *************************************************************************

    @Override
    public Class<T> getType() {
        return m_type;
    }

    @Override
    public T deserialize(InputStream in) throws Exception {
        return m_mapper.readValue(in,m_type);
    }

    @Override
    public void serialize(OutputStream out, T object) throws Exception {
        m_mapper.writeValue(out, object);
    }
}
