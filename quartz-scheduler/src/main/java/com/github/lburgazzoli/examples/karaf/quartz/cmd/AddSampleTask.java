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
import com.github.lburgazzoli.examples.karaf.quartz.TaskConstants;
import com.github.lburgazzoli.examples.karaf.quartz.TaskDefinition;
import org.apache.felix.gogo.commands.Command;

import java.util.UUID;

/**
 *
 */
@Command(scope = "task", name = "sample-add", description = "Add Sample TaskExecutor")
public class AddSampleTask extends AbstractSchedulerCommand {
    @Override
    protected void doExecute(ITaskScheduler service) throws Exception {

        String uriStr = String.format("%s://%s/filter?jndi=(osgi.jndi.service.name=test)",
            TaskConstants.TASK_DEF_TYPE_OSGI,
            Runnable.class.getName());

        TaskDefinition td = new TaskDefinition();
        td.put(TaskConstants.TASK_DEF_URI  , uriStr);
        td.put(TaskConstants.TASK_DEF_CRON , "0 0/1 * 1/1 * ? *");
        td.put(TaskConstants.TASK_DEF_ID   , UUID.randomUUID().toString());
        td.put(TaskConstants.TASK_DEF_GROUP,"samples");

        getService().schedule(td);
    }
}
