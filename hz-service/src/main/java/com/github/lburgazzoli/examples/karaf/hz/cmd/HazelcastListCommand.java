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
package com.github.lburgazzoli.examples.karaf.hz.cmd;

import com.github.lburgazzoli.examples.karaf.hz.IHazelcastInstanceProvider;
import com.github.lburgazzoli.karaf.common.cmd.ShellTable;
import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.core.Partition;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

/**
 *
 */
@Command(scope = "hz", name = "list", description = "Lists")
public class HazelcastListCommand extends AbstractHazelcastCommand {

    @Argument(
        index       = 0,
        name        = "arg",
        description = "The command argument",
        required    = true,
        multiValued = false)
    String arg = null;

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void doExecute(IHazelcastInstanceProvider service) throws Exception {
        if(StringUtils.isNotBlank(arg)) {
            if(StringUtils.equalsIgnoreCase(HazelcastCommandConstants.LIST_MEMBERS,arg)) {
                doExecuteMemberList(service.getInstance());
            } else if(StringUtils.equalsIgnoreCase(HazelcastCommandConstants.LIST_OBJECTS,arg)) {
                doExecuteDistributedObjectList(service.getInstance());
            } else if(StringUtils.equalsIgnoreCase(HazelcastCommandConstants.LIST_PARTITIONS,arg)) {
                doExecutePartitionList(service.getInstance());
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
    public void doExecuteMemberList(HazelcastInstance instance) throws Exception {
        ShellTable table = new ShellTable("ID","Host","Port","Local");

        for(Member m : instance.getCluster().getMembers()) {
            table.addRow(
                m.getUuid(),
                m.getInetSocketAddress().getHostName(),
                m.getInetSocketAddress().getPort(),
                m.localMember() ? "*" : StringUtils.EMPTY
            );
        }

        table.print();
    }

    /**
     *
     * @param instance
     * @throws Exception
     */
    public void doExecuteDistributedObjectList(HazelcastInstance instance) throws Exception {
        ShellTable table = new ShellTable("ID","Name","Class");

        for(DistributedObject o : instance.getDistributedObjects()) {
            table.addRow(
                o.getId(),
                o.getName(),
                o.getClass()
            );
        }

        table.print();
    }

    /**
     *
     * @param instance
     * @throws Exception
     */
    public void doExecutePartitionList(HazelcastInstance instance) throws Exception {
        ShellTable table = new ShellTable("ID","Member");

        for(Partition p : instance.getPartitionService().getPartitions()) {
            table.addRow(
                p.getPartitionId(),
                p.getOwner().getUuid()
            );
        }

        table.print();
    }
}
