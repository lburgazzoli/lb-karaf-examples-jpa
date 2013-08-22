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

import com.github.lburgazzoli.examples.karaf.quartz.ITaskScheduler;
import com.github.lburgazzoli.osgi.karaf.cmd.ShellTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.gogo.commands.Command;
import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;

/**
 *
 */
@Command(
    scope       = "scheduler",
    name        = "task-list",
    description = "TaskExecutor list")
public class TaskListCommand extends AbstractSchedulerCommand {

    /**
     * c-tor
     */
    public TaskListCommand() {
    }

    // *************************************************************************
    //
    // ************************************************************************

    @Override
    protected void doExecute(ITaskScheduler service) throws Exception {
        Scheduler  scheduler = service.getScheduler();
        ShellTable table     = new ShellTable("Job","Trigger","Expression","NextFireTime");

        for(String group : scheduler.getJobGroupNames()) {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group))) {
                for(Trigger trigger : scheduler.getTriggersOfJob(jobKey)) {

                    String expression = StringUtils.EMPTY;
                    if(trigger instanceof CronTrigger) {
                        expression = ((CronTrigger)trigger).getCronExpression();
                    } else if(trigger instanceof SimpleTrigger) {
                        expression = "count="
                            + ((SimpleTrigger)trigger).getRepeatCount()
                            + ", interval="
                            + ((SimpleTrigger)trigger).getRepeatInterval();
                    }

                    table.addRow(
                        jobKey.getGroup() + "/" + jobKey.getName(),
                        trigger.getKey().getGroup() + "/" + trigger.getKey().getName(),
                        expression,
                        trigger.getNextFireTime());

                }
            }
        }

        table.print();
    }
}
