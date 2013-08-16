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
package com.github.lburgazzoli.examples.hazelcast.data.cmd;

import com.github.lburgazzoli.examples.hazelcast.data.HzData;
import com.github.lburgazzoli.osgi.hazelcast.IHazelcastInstanceProvider;
import com.github.lburgazzoli.osgi.hazelcast.cmd.AbstractHazelcastCommand;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

/**
 *
 */

@Command(scope = "hztest", name = "broadcast", description = "data")
public class HzDataBroadcastCommand extends AbstractHazelcastCommand {

    @Argument(
        index       = 0,
        name        = "val",
        description = "The val",
        required    = true,
        multiValued = false)
    String val = null;

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void doExecute(IHazelcastInstanceProvider service) throws Exception {
        if(StringUtils.isNotBlank(val)) {
            service.getInstance().getTopic("hztest:topic").publish(new HzData(val));
        }
    }
}
