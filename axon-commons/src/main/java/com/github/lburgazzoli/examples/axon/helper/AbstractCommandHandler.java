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
package com.github.lburgazzoli.examples.axon.helper;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.unitofwork.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public abstract class AbstractCommandHandler<T> implements CommandHandler<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommandHandler.class);

    private final Class<T> m_type;

    /**
     *
     * @param type
     */
    public AbstractCommandHandler(Class<T> type) {
        m_type = type;
    }

    @Override
    public Object handle(CommandMessage<T> commandMessage, UnitOfWork unitOfWork) throws Throwable {
        if(m_type.equals(commandMessage.getPayloadType())) {
            return doHandle(m_type.cast(commandMessage.getPayload()));
        }

        return null;
    }

    /**
     *
     * @param data
     */
    protected abstract Object doHandle(T data) throws Throwable;
}
