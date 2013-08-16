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
package com.github.lburgazzoli.osgi.hazelcast.cmd;

import com.github.lburgazzoli.osgi.OSGiClassLoader;
import com.github.lburgazzoli.osgi.karaf.cmd.AbstractServiceCommand;
import com.github.lburgazzoli.osgi.karaf.cmd.ShellTable;
import org.apache.felix.gogo.commands.Command;
import org.osgi.framework.Bundle;

/**
 *
 */

@Command(scope = "hz", name = "cl-bundles", description = "List bundles")
public class ClassLoaderBundleListCommand extends AbstractServiceCommand<OSGiClassLoader> {
    @Override
    protected void doExecute(OSGiClassLoader service) throws Exception {
        ShellTable table = new ShellTable("ID","Symbolic Name");

        for(Bundle b : service.getBundles()) {
            table.addRow(
                b.getBundleId(),
                b.getSymbolicName()
            );
        }

        table.print();
    }
}
