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
package com.github.lburgazzoli.examples.axon.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lburgazzoli.json.JacksonHelper;
import org.axonframework.serializer.AnnotationRevisionResolver;
import org.axonframework.serializer.ChainingConverterFactory;
import org.axonframework.serializer.ContentTypeConverter;
import org.axonframework.serializer.ConverterFactory;
import org.axonframework.serializer.RevisionResolver;
import org.axonframework.serializer.SerializationException;
import org.axonframework.serializer.SerializedObject;
import org.axonframework.serializer.SerializedType;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.SimpleSerializedObject;
import org.axonframework.serializer.SimpleSerializedType;
import org.axonframework.serializer.UnknownSerializedTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class JacksonSerializer implements Serializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonSerializer.class);

    private final ClassLoader m_classLoader;
    private final ObjectMapper m_objectMapper;
    private final RevisionResolver m_revisionResolver;
    private final ConverterFactory m_converterFactory;

    /**
     * c-tor
     */
    public JacksonSerializer(ClassLoader classLoader) {
        this(classLoader, JacksonHelper.DEFAULT_JSON);
    }

    /**
     * c-tor
     *
     * @param objectMapper
     */
    public JacksonSerializer(ClassLoader classLoader,ObjectMapper objectMapper) {
        m_classLoader      = classLoader;
        m_objectMapper     = objectMapper;
        m_revisionResolver = new AnnotationRevisionResolver();
        m_converterFactory = new JacksonConverterFactory();
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public <T> SerializedObject<T> serialize(Object instance, Class<T> expectedType) {
        byte[] data;

        try {
            data = m_objectMapper.writeValueAsBytes(instance);
        } catch (JsonProcessingException e) {
            throw new SerializationException("An exception occurred writing serialized data",e);
        }

        return new SimpleSerializedObject<T>(
            convert(byte[].class, expectedType, data),
            expectedType,
            instance.getClass().getName(),
            revisionOf(instance.getClass()));
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public <S, T> T deserialize(SerializedObject<S> serializedObject) {
        T data = null;

        if(byte[].class == serializedObject.getContentType()) {
            try {
                data = (T)m_objectMapper.readValue(
                    byte[].class.cast(serializedObject.getData()),
                    classForType(serializedObject.getType()));
            } catch (Exception e) {
                LOGGER.warn("An exception occurred writing deserialized data",e);
            }
        }

        return data;
    }

    @Override
    public <T> boolean canSerializeTo(Class<T> expectedRepresentation) {
        return m_objectMapper.canSerialize(expectedRepresentation);
    }

    @Override
    public Class classForType(SerializedType type) throws UnknownSerializedTypeException {
        try {
            return m_classLoader.loadClass(type.getName());
        } catch (ClassNotFoundException e) {
            throw new UnknownSerializedTypeException(type, e);
        }
    }

    @Override
    public SerializedType typeForClass(Class type) {
        return new SimpleSerializedType(type.getName(),revisionOf(type));
    }

    @Override
    public ConverterFactory getConverterFactory() {
        return m_converterFactory;
    }

    // *************************************************************************
    // helpers
    // *************************************************************************

    private String revisionOf(Class<?> type) {
        return m_revisionResolver.revisionOf(type);
    }

    protected <S, T> T convert(Class<S> sourceType, Class<T> targetType, S source) {
        return m_converterFactory.getConverter(sourceType, targetType).convert(source);
    }

    // *************************************************************************
    //
    // *************************************************************************

    private class JacksonConverterFactory implements ConverterFactory {
        private final ConverterFactory m_convertedFactoryDelegate;

        /**
         * c-tor
         */
        public JacksonConverterFactory() {
            m_convertedFactoryDelegate = new ChainingConverterFactory();
        }

        @Override
        public <S, T> boolean hasConverter(Class<S> sourceContentType, Class<T> targetContentType) {
            return m_convertedFactoryDelegate.hasConverter(sourceContentType,targetContentType);
        }

        @Override
        public <S, T> ContentTypeConverter<S, T> getConverter(Class<S> sourceContentType, Class<T> targetContentType) {
            return m_convertedFactoryDelegate.getConverter(sourceContentType,targetContentType);
        }
    }
}
