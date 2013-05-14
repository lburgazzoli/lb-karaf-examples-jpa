package com.github.lburgazzoli.examples.karaf.zookeeper.client.cmd;

import com.github.lburgazzoli.examples.karaf.cmd.AbstractServiceCommand;
import com.github.lburgazzoli.examples.karaf.zookeeper.client.IZKClient;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

/**
 *
 */
@Command(scope="zk", name="node-list", description="Get Childrens")
public class ZKList extends AbstractServiceCommand<IZKClient> {

    @Argument(index=0, required=true, multiValued=false, name="Path")
    String path;

    @Override
    protected void doExecute(IZKClient service) throws Exception {
        for(String item : service.list(path)) {
            System.out.println("> " + item);
        }
    }
}
