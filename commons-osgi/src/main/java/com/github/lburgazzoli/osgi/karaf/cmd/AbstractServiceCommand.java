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
package com.github.lburgazzoli.osgi.karaf.cmd;

import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 *
 */
public abstract class AbstractServiceCommand <T> extends OsgiCommandSupport {

    private T m_service;

    /**
     * c-tor
     */
    public AbstractServiceCommand() {
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
    protected Object doExecute() throws Exception {
        doExecute(m_service);
        return null;
    }

    /**
     *
     * @param service
     * @throws Exception
     */
    protected abstract void doExecute(T service) throws Exception;
}
