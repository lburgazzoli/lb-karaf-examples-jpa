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
package com.github.lburgazzoli.examples.karaf.hz.data.cmd;

import com.github.lburgazzoli.examples.karaf.hz.IHazelcastInstanceProvider;
import com.github.lburgazzoli.examples.karaf.hz.cmd.AbstractHazelcastCommand;
import com.github.lburgazzoli.examples.karaf.hz.data.HzData;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

import java.util.List;

/**
 *
 */
@Command(scope = "hztest", name = "data", description = "data")
public class HzDataCommand extends AbstractHazelcastCommand {

    public static final String TYPE_MAP   = "map";
    public static final String TYPE_EVT   = "evt";

    public static final String CMD_PUT    = "put";
    public static final String CMD_GET    = "get";
    public static final String CMD_QUERY  = "query";
    public static final String CMD_SEND   = "send";

    // *************************************************************************
    //
    // *************************************************************************

    @Argument(
        index       = 0,
        name        = "type",
        description = "The type",
        required    = true,
        multiValued = false)
    String type = null;

    @Argument(
        index       = 1,
        name        = "action",
        description = "The action",
        required    = true,
        multiValued = false)
    String action = null;

    @Argument(
        index       = 2,
        name        = "args",
        description = "The key",
        required    = true,
        multiValued = true)
    List<String> args = null;

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void execute() throws Exception {
        if(StringUtils.equalsIgnoreCase(TYPE_MAP,type)) {
            if(StringUtils.equalsIgnoreCase(CMD_PUT,action)) {
                doExecuteMapPut(getService().getInstance());
            } else if(StringUtils.equalsIgnoreCase(CMD_GET,action)) {
                doExecuteMapGet(getService().getInstance());
            } else if(StringUtils.equalsIgnoreCase(CMD_QUERY,action)) {
                doExecuteMapQuery(getService().getInstance());
            }
        } else if(StringUtils.equalsIgnoreCase(TYPE_EVT,type)) {
            if(StringUtils.equalsIgnoreCase(CMD_SEND,action)) {
                doExecuteEvtSend(getService().getInstance());
            }
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
    public void doExecuteMapPut(HazelcastInstance instance) throws Exception {
        if(StringUtils.isNotBlank(action) && (args.size() == 2)) {

            HzData data = (HzData)instance.getMap("hztest:map").put(
                args.get(0),
                new HzData(args.get(1)));

            System.out.println("put: <" + data + ">");
        }
    }

    /**
     *
     * @param instance
     * @throws Exception
     */
    public void doExecuteMapGet(HazelcastInstance instance) throws Exception {
        if(StringUtils.isNotBlank(action) && (args.size() == 1)) {
            HzData data = (HzData)instance.getMap("hztest:map").get(args.get(0));
            System.out.println("get: <" + data + ">");
        }
    }

    /**
     *
     * @param instance
     * @throws Exception
     */
    public void doExecuteMapQuery(HazelcastInstance instance) throws Exception {
        if(StringUtils.isNotBlank(action) && (args.size() == 1)) {
            IMap<String,HzData> data = instance.getMap("hztest:map");
            for(HzData d : data.values(new SqlPredicate(args.get(0)))) {
                System.out.println("result: <" + d + ">");
            }
        }
    }

    /**
     *
     * @param instance
     * @throws Exception
     */
    public void doExecuteEvtSend(HazelcastInstance instance) throws Exception {
        if(StringUtils.isNotBlank(action) && (args.size() == 1)) {
            instance.getTopic("hztest:topic").publish(new HzData(args.get(0)));
        }
    }

}
