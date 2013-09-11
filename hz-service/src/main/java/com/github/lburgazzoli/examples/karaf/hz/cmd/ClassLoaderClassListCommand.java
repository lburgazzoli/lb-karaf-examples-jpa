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
package com.github.lburgazzoli.examples.karaf.hz.cmd;

import com.github.lburgazzoli.karaf.common.CombinedClassLoader;
import com.github.lburgazzoli.karaf.common.cmd.AbstractServiceCommand;
import com.github.lburgazzoli.karaf.common.cmd.ShellTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.osgi.framework.Bundle;

/**
 *
 */
@Command(scope = "hz", name = "cl-classes", description = "List classes")
public class ClassLoaderClassListCommand extends AbstractServiceCommand<CombinedClassLoader> {

    @Argument(
        index       = 0,
        name        = "symbolicName",
        description = "The symbolic name",
        required    = false,
        multiValued = false)
    String symbolicName = null;

    @Override
    protected void execute() throws Exception {
        ShellTable table = new ShellTable("Class","Bundle ID","Bundle Symbolic Name");

        if(StringUtils.isNotBlank(symbolicName)) {
            for(Class<?> type : getService().getClassesForBundle(symbolicName)) {
                table.addRow(type.getName(),"",symbolicName);
            }
        } else {
            for(Bundle b : getService().getBundles()) {
                for(Class<?> type : getService().getClassesForBundle(b)) {
                    table.addRow(type.getName(),b.getBundleId(),b.getSymbolicName());
                }
            }
        }

        table.print();
    }
}
