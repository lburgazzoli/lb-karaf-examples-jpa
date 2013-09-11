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
package com.github.lburgazzoli.examples.karaf.quartz.cmd;

import com.github.lburgazzoli.karaf.common.cmd.ShellTable;
import org.apache.felix.gogo.commands.Command;
import org.quartz.SchedulerMetaData;

/**
 *
 */
@Command(
    scope       = "scheduler",
    name        = "scheduler-info",
    description = "TaskScheduler info")
public class TaskSchedulerInfoCommand extends AbstractSchedulerCommand {
    /**
     * c-tor
     */
    public TaskSchedulerInfoCommand() {
    }

    // *************************************************************************
    //
    // ************************************************************************

    @Override
    protected void execute() throws Exception {
        ShellTable        table = new ShellTable("Key","Val");
        SchedulerMetaData meta  = getService().getScheduler().getMetaData();

        table.addRow("Name"        ,meta.getSchedulerName());
        table.addRow("Version"     ,meta.getVersion());
        table.addRow("RunningSince",meta.getRunningSince());
        table.addRow("Started"     ,meta.isStarted());

        table.print();
    }
}
