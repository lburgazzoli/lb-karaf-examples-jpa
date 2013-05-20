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
package com.github.lburgazzoli.examples.karaf.axon.engine.karaf;

import com.github.lburgazzoli.osgi.OSGiClassLoader;
import com.github.lburgazzoli.osgi.karaf.cmd.AbstractTabularCommand;
import com.github.lburgazzoli.osgi.karaf.cmd.ShellTable;
import com.google.common.collect.Lists;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
@Command(scope = "axon", name = "list-loadedclasses-for-bundle", description = "List Loaded Classes For Bundles")
public class ListLoadedClassesForBundle extends AbstractTabularCommand<OSGiClassLoader> {

    @Argument(index=0,required=true,multiValued=false,name="BundleSymbolicName",description="BundleSymbolicName")
    String bundleSymbolicName;

    /**
     * c-tor
     */
    public ListLoadedClassesForBundle() {
        super("ClassName");
        super.setMxColSize(120);
    }

    @Override
    public void doExecute(OSGiClassLoader engine,ShellTable table) throws Exception {
        List<Class<?>> classes = Lists.newArrayList(engine.getClassesForBundle(bundleSymbolicName));
        Collections.sort(classes,new Comparator<Class<?>>() {
            @Override
            public int compare(Class<?> o1, Class<?> o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for(Class<?> type : classes) {
            table.addRow(type.getName());
        }
    }
}