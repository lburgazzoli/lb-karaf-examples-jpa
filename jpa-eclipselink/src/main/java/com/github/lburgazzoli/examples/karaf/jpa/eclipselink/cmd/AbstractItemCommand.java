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
package com.github.lburgazzoli.examples.karaf.jpa.eclipselink.cmd;

import com.github.lburgazzoli.examples.karaf.jpa.commons.data.IDataService;
import com.github.lburgazzoli.examples.karaf.jpa.eclipselink.data.Item;
import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 *
 */
public abstract class AbstractItemCommand extends OsgiCommandSupport {
    private IDataService<Item> m_dataService;

    public void setDataService(IDataService<Item> dataService) {
        m_dataService = dataService;
    }

    public IDataService<Item> getDataService() {
        return m_dataService;
    }
}
