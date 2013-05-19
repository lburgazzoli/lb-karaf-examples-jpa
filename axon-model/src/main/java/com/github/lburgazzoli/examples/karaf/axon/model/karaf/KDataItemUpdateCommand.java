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
package com.github.lburgazzoli.examples.karaf.axon.model.karaf;

import com.github.lburgazzoli.examples.axon.IAxonEngine;
import com.github.lburgazzoli.examples.axon.helper.CommandCallbackAdapter;
import com.github.lburgazzoli.examples.karaf.axon.model.commands.DataItemUpdateCommand;
import com.github.lburgazzoli.osgi.karaf.cmd.AbstractServiceCommand;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@Command(scope = "axon", name = "dataitem-update", description = "Update DataItem")
public class KDataItemUpdateCommand extends AbstractServiceCommand<IAxonEngine> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KDataItemUpdateCommand.class);

    @Argument(index=0,required=true,multiValued=false,name="Id",description="Id")
    String id;

    @Argument(index=1,required=true,multiValued=false,name="Text",description="Text")
    String text;


    @Override
    public void doExecute(IAxonEngine engine) throws Exception {
        engine.send(new DataItemUpdateCommand(id,text),new CommandCallbackAdapter<Object>() {
            @Override
            public void onFailure(Throwable cause) {
                LOGGER.debug("onFailure => <{}> ",cause.getClass().getName());
                LOGGER.debug("onFailure",cause);
            }
        });
    }
}
