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
package com.github.lburgazzoli.serialization.impl;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.lburgazzoli.serialization.ITypeSerializer;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
public class KryoTypeSerializer<T> implements ITypeSerializer<T> {
    private final Kryo m_kryo;
    private final Class<T> m_type;

    /**
     * c-tor
     *
     * @param type
     */
    public KryoTypeSerializer(Class<T> type) {
        this(new Kryo(), type);
    }

    /**
     * c-tor
     *
     * @param kryo
     * @param type
     */
    public KryoTypeSerializer(Kryo kryo,Class<T> type) {
        m_kryo = kryo;
        m_type = type;
    }

    // *************************************************************************
    // ITypeSerializer<T>
    // *************************************************************************

    @Override
    public Class<T> getType() {
        return m_type;
    }

    @Override
    public T deserialize(InputStream in)  throws Exception {
        return m_kryo.readObject(new Input(in),m_type);
    }

    @Override
    public void serialize(OutputStream out, T object)  throws Exception {
        m_kryo.writeObject(new Output(out),object);
    }
}
