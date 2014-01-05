/*
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
package com.github.lburgazzoli.examples.karaf.jpa.hibernate.noaries.cmd;

import com.github.lburgazzoli.examples.karaf.jpa.hibernate.noaries.data.Item;
import com.github.lburgazzoli.karaf.common.cmd.CommandShellTable;
import org.apache.karaf.shell.commands.Command;


/**
 *
 */
@Command(
    scope       = "item",
    name        = "hibernate-noa-am-list",
    description = "Lists all items (AM)")
public class AMItemListCommand extends AbstractItemCommand {

    @Override
    public void doExecuteCommand() throws Exception {
        CommandShellTable table = new CommandShellTable("Key","Value");
        for (Item item : getDataService().getAll()) {
            table.row(item.getName(),item.getDescription());
        }

        table.print();
    }
}
