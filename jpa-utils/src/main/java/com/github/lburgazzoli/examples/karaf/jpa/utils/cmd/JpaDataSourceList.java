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
package com.github.lburgazzoli.examples.karaf.jpa.utils.cmd;

import com.github.lburgazzoli.examples.karaf.jpa.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.gogo.commands.Command;
import org.osgi.framework.ServiceReference;

import javax.sql.DataSource;

/**
 *
 */
@Command(scope = "jpa", name = "datasource-list", description = "List DataSource Providers")
public class JpaDataSourceList extends AbstractTabularCommand {

    public JpaDataSourceList() {
        super("BundleID","BundleName","JNDI");
    }

    @Override
    public void doExecuteCommand() throws Exception {
        for(ServiceReference sr : getServiceReferences(DataSource.class)) {
            String providerClass = (String)sr.getProperty(Constants.JPA_JNDI_DS_SVCP);
            if(StringUtils.isNotBlank(providerClass)) {
                addRow(
                    sr.getBundle().getBundleId(),
                    sr.getBundle().getSymbolicName(),
                    providerClass);
            }
        }
    }
}
