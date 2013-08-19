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
package com.github.lburgazzoli.examples.karaf.axon.engine.karaf;

import com.github.lburgazzoli.osgi.OSGiClassLoader;
import com.github.lburgazzoli.osgi.karaf.cmd.AbstractTabularCommand;
import com.github.lburgazzoli.osgi.karaf.cmd.ShellTable;
import com.google.common.collect.Lists;
import org.apache.felix.gogo.commands.Command;
import org.osgi.framework.Bundle;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
@Command(scope = "axon", name = "list-classloader-bundles", description = "List ClassLoader Bundles")
public class ListClassLoaderBundles extends AbstractTabularCommand<OSGiClassLoader> {
    /**
     * c-tor
     */
    public ListClassLoaderBundles() {
        super("BundleId","SymbolicName");
        super.setMxColSize(64);
    }

    @Override
    public void doExecute(OSGiClassLoader engine,ShellTable table) throws Exception {
        List<Bundle> bundles = Lists.newArrayList(engine.getBundles());
        Collections.sort(bundles,new Comparator<Bundle>() {
            @Override
            public int compare(Bundle o1, Bundle o2) {
                return o1.getSymbolicName().compareTo(o2.getSymbolicName());
            }
        });

        for(Bundle bundle : bundles) {
            table.addRow(bundle.getBundleId(),bundle.getSymbolicName());
        }
    }
}