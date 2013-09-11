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
package com.github.lburgazzoli.examples.karaf.zookeeper.client.cmd;

import com.github.lburgazzoli.examples.karaf.zookeeper.client.IZKClient;
import com.github.lburgazzoli.karaf.common.cmd.AbstractServiceCommand;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

/**
 *
 */
@Command(scope="zk", name="node-create", description="Node Create")
public class ZKCreate extends AbstractServiceCommand<IZKClient> {

    @Argument(index=0, required=true, multiValued=false, name="Path")
    String path;

    @Argument(index=1, required=false, multiValued=false, name="Data")
    String data;

    @Override
    protected void execute() throws Exception {
        getService().create(path, data);
    }
}
