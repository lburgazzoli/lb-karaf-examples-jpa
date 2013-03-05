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
package lb.examples.karaf.jpa.openjpa.cmd;

import lb.examples.karaf.jpa.openjpa.data.IDataService;
import lb.examples.karaf.jpa.openjpa.data.Item;
import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

/**
 *
 */
@Command(scope = "item", name = "am-list", description = "Lists all items (AM)")
public class CMItemListCommand implements Action {
    private IDataService m_dataService;

    public void setDataService(IDataService dataService) {
        m_dataService = dataService;
    }

    @Override
    public Object execute(CommandSession session) throws Exception {
        for (Item item : m_dataService.getAll()) {
            System.out.println(item.getName() + ", " + item.getDescription());
        }

        return null;
    }
}
