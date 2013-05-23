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
package com.github.lburgazzoli.examples.karaf.axon.engine.karaf;

import com.github.lburgazzoli.Utils;
import com.github.lburgazzoli.examples.axon.store.HazelcastDomainEventStore;
import com.github.lburgazzoli.examples.axon.store.IHazelcastEventStore;
import com.github.lburgazzoli.osgi.karaf.cmd.AbstractTabularCommand;
import com.github.lburgazzoli.osgi.karaf.cmd.ShellTable;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.axonframework.domain.DomainEventMessage;

/**
 *
 */
@Command(scope = "axon", name = "hz-storage-browse", description = "Hazelcast Storage Browse")
public class HazelcastStorageBrowse extends AbstractTabularCommand<IHazelcastEventStore> {
    @Argument(index=0,required=true,multiValued=false,name="StorageId",description="StorageId")
    String storageId;

    /**
     * c-tor
     */
    public HazelcastStorageBrowse() {
        super("Timestamp","SequenceNumber","Payload");
        super.setMxColSize(512);
    }

    @Override
    public void doExecute(IHazelcastEventStore storage,ShellTable table) throws Exception {


        HazelcastDomainEventStore eventStore = storage.getDomainEventStore(storageId);
        if(eventStore != null) {

            ClassLoader cl =
                Utils.swapContextClassLoader(eventStore.getClassLoader());

            try {
                for(DomainEventMessage data : eventStore.getStorage()) {
                    table.addRow(
                        data.getTimestamp(),
                        data.getSequenceNumber(),
                        data.getPayload().toString());
                }
            } finally {
                Utils.swapContextClassLoader(cl);
            }
        }
    }
}