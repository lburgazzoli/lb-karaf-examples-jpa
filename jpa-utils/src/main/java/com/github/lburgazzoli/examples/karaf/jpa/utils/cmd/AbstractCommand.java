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
package com.github.lburgazzoli.examples.karaf.jpa.utils.cmd;

import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.ServiceReference;

/**
 *
 */
public abstract class AbstractCommand extends OsgiCommandSupport {
    public static final ServiceReference[] EMPTY_REFERENCE_ARRAY = new ServiceReference[0];

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object doExecute() throws Exception {
        doExecuteCommand();
        return null;
    }

    /**
     *
     * @throws Exception
     */
    public abstract void doExecuteCommand() throws Exception;

    /**
     *
     * @param type
     * @return
     * @throws Exception
     */
    protected ServiceReference[] getServiceReferences(Class type) throws Exception {
        return getServiceReferences(type.getName());
    }

    /**
     *
     * @param type
     * @return
     * @throws Exception
     */
    protected ServiceReference[] getServiceReferences(String type) throws Exception {
        ServiceReference[] refs = getBundleContext().getServiceReferences(type,null);
        return refs != null ? refs : EMPTY_REFERENCE_ARRAY;
    }
}
