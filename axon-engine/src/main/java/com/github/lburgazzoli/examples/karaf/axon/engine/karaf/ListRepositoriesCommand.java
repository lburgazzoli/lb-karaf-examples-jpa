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

import com.github.lburgazzoli.examples.axon.IAxonEngine;
import com.github.lburgazzoli.karaf.common.cmd.AbstractServiceCommand;
import com.github.lburgazzoli.karaf.common.cmd.ShellTable;
import org.apache.felix.gogo.commands.Command;
import org.axonframework.eventsourcing.EventSourcingRepository;


/**
 *
 */
@Command(scope = "axon", name = "list-repos", description = "List repositories")
public class ListRepositoriesCommand extends AbstractServiceCommand<IAxonEngine> {
    /**
     * c-tor
     */
    public ListRepositoriesCommand() {
    }

    @Override
    public void execute() throws Exception {
        ShellTable table = new ShellTable("AGGREGATE_TYPE","REPO_CLASS");
        table.setMxColSize(64);

        for(EventSourcingRepository<?> repository : getService().getRepositories()) {
            table.addRow(repository.getTypeIdentifier(),repository.getClass().getName());
        }

        table.print();
    }
}
