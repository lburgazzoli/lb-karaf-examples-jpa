package com.github.lburgazzoli.examples.karaf.zookeeper.client.cmd;

import com.github.lburgazzoli.examples.karaf.cmd.AbstractServiceCommand;
import com.github.lburgazzoli.examples.karaf.zookeeper.client.IZKClient;
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
    protected void doExecute(IZKClient service) throws Exception {
        service.create(path,data);
    }
}
