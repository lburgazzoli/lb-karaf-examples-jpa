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
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

/**
 *
 */
@Command(scope = "hztest", name = "data", description = "data")
public class HzDataCommand extends AbstractHazelcastCommand {

    public static final String CMD_PUT   = "put";
    public static final String CMD_GET   = "get";
    public static final String CMD_QUERY = "query";

    // *************************************************************************
    //
    // *************************************************************************

    @Argument(
        index       = 0,
        name        = "action",
        description = "The action",
        required    = true,
        multiValued = false)
    String action = null;

    @Argument(
        index       = 1,
        name        = "key",
        description = "The key",
        required    = true,
        multiValued = false)
    String key = null;

    @Argument(
        index       = 2,
        name        = "val",
        description = "The val",
        required    = false,
        multiValued = false)
    String val = null;

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void doExecute(IHazelcastInstanceProvider service) throws Exception {
        if(StringUtils.equalsIgnoreCase(CMD_PUT,action)) {
            doExecutePut(service.getInstance());
        } else if(StringUtils.equalsIgnoreCase(CMD_GET,action)) {
            doExecuteGet(service.getInstance());
        } else if(StringUtils.equalsIgnoreCase(CMD_QUERY,action)) {
            doExecuteQuery(service.getInstance());
        }
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param instance
     * @throws Exception
     */
    public void doExecutePut(HazelcastInstance instance) throws Exception {
        if( StringUtils.isNotBlank(action) &&
            StringUtils.isNotBlank(key   ) &&
            StringUtils.isNotBlank(val   ) ) {

            HzData data = (HzData)instance.getMap("hztest:map").put(key,new HzData(val));
            System.out.println("put: <" + data + ">");
        }
    }

    /**
     *
     * @param instance
     * @throws Exception
     */
    public void doExecuteGet(HazelcastInstance instance) throws Exception {
        if( StringUtils.isNotBlank(action) &&
            StringUtils.isNotBlank(key   ) ) {

            HzData data = (HzData)instance.getMap("hztest:map").get(key);
            System.out.println("get: <" + data + ">");
        }
    }

    /**
     *
     * @param instance
     * @throws Exception
     */
    public void doExecuteQuery(HazelcastInstance instance) throws Exception {
        if( StringUtils.isNotBlank(action) &&
            StringUtils.isNotBlank(key   ) ) {

            IMap<String,HzData> data = instance.getMap("hztest:map");
            for(HzData d : data.values(new SqlPredicate(key))) {
                System.out.println("result: <" + d + ">");
            }
        }
    }

}
