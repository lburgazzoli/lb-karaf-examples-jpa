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

/**
 *
 */
public abstract class AbstractTabularCommand<T> extends AbstractServiceCommand<T> {

    private ShellTable m_table;

    /**
     * c-tor
     */
    public AbstractTabularCommand(String... columns) {
        m_table = new ShellTable(columns);
    }

    /**
     *
     * @param size
     */
    protected void setMxColSize(int size) {
        m_table.setMxColSize(size);
    }

    /**
     *
     * @return
     */
    protected ShellTable getTable() {
        return m_table;
    }

    /**
     *
     * @param service
     * @throws Exception
     */
    protected void doExecute(T service) throws Exception {
        doExecute(service,m_table);
        m_table.print();;
    }

    /**
     *
     * @param service
     * @throws Exception
     */
    protected abstract void doExecute(T service,ShellTable table) throws Exception;

}
