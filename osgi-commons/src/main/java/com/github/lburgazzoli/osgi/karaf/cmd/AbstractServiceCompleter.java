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
package com.github.lburgazzoli.osgi.karaf.cmd;

import org.apache.karaf.shell.console.Completer;

import java.util.List;

/**
 *
 */
public abstract class AbstractServiceCompleter<T> implements Completer {

    private T m_service;

    /**
     * c-tor
     */
    public AbstractServiceCompleter() {
        m_service = null;
    }

    /**
     *
     * @param service
     */
    public void setService(T service) {
        m_service = service;
    }

    /**
     *
     * @return
     */
    public T getService() {
        return m_service;
    }

    @Override
    public int complete(String buffer, int cursor, List<String> candidates) {
        return doComplete(m_service,buffer,cursor,candidates);
    }

    /**
     *
     * @param service
     * @param buffer
     * @param cursor
     * @param candidates
     * @return
     */
    protected abstract int doComplete(T service,String buffer, int cursor, List<String> candidates);
}
