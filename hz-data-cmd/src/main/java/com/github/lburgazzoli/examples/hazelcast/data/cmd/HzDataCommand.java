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
import com.github.lburgazzoli.osgi.hazelcast.IHazelcastInstanceProxy;
import com.github.lburgazzoli.osgi.hazelcast.cmd.AbstractHazelcastCommand;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

import java.util.List;

/**
 *
 */
@Command(scope = "hz", name = "data", description = "data")
public class HzDataCommand extends AbstractHazelcastCommand {

    @Argument(
        index       = 0,
        name        = "map",
        description = "The map",
        required    = true,
        multiValued = false)
    String map = null;

    @Argument(
        index       = 1,
        name        = "action",
        description = "The action",
        required    = true,
        multiValued = false)
    String action = null;

    @Argument(
        index       = 2,
        name        = "key",
        description = "The key",
        required    = true,
        multiValued = false)
    String key = null;

    @Argument(
        index       = 3,
        name        = "val",
        description = "The val",
        required    = false,
        multiValued = false)
    String val = null;

    // *************************************************************************
    //
    // *************************************************************************

    /**
     * c-tor
     */
    public HzDataCommand() {
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public Object doExecute() throws Exception {
        List<IHazelcastInstanceProxy> proxies =
            getAllServices(IHazelcastInstanceProxy.class,null);

        try {
            if(proxies.size() == 1) {
                IHazelcastInstanceProxy proxy = proxies.get(0);

                if(StringUtils.equalsIgnoreCase("put",action)) {
                    doExecutePut(proxy);
                } else if(StringUtils.equalsIgnoreCase("get",action)) {
                    doExecuteGet(proxy);
                }
            }
        } finally {
            ungetServices();
        }

        return null;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param proxy
     * @throws Exception
     */
    public void doExecutePut(IHazelcastInstanceProxy proxy) throws Exception {
        if( StringUtils.isNotBlank(map   ) &&
            StringUtils.isNotBlank(action) &&
            StringUtils.isNotBlank(key   ) &&
            StringUtils.isNotBlank(val   ) ) {

            HzData data = (HzData)proxy.getMap(map).put(key,new HzData(val));
            System.out.println("put: <" + data + ">");
        }
    }

    /**
     *
     * @param proxy
     * @throws Exception
     */
    public void doExecuteGet(IHazelcastInstanceProxy proxy) throws Exception {
        if( StringUtils.isNotBlank(map   ) &&
            StringUtils.isNotBlank(action) &&
            StringUtils.isNotBlank(key   ) ) {

            HzData data = (HzData)proxy.getMap(map).get(key);
            System.out.println("get: <" + data + ">");
        }
    }

}
