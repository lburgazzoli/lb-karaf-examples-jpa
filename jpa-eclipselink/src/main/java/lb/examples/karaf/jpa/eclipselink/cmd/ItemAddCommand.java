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
package lb.examples.karaf.jpa.eclipselink.cmd;

import lb.examples.karaf.jpa.eclipselink.data.ApplicationManagedDataService;
import lb.examples.karaf.jpa.eclipselink.data.Item;
import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

/**
 *
 */
@Command(scope = "item", name = "add", description = "Add and item")
public class ItemAddCommand implements Action {
    @Argument(
        index       = 0,
        name        = "Name",
        required    = true,
        description = "Item Name",
        multiValued = false)
    String name;

    @Argument(
        index       = 1,
        name        = "Description",
        required    = true,
        description = "Item Description",
        multiValued = false)
    String description;

    private ApplicationManagedDataService m_applicationManagedDataService;

    public void setApplicationManagedDataService(ApplicationManagedDataService applicationManagedDataService) {
        this.m_applicationManagedDataService = applicationManagedDataService;
    }

    @Override
    public Object execute(CommandSession session) throws Exception {
        m_applicationManagedDataService.add(new Item(name,description));
        return null;
    }
}
