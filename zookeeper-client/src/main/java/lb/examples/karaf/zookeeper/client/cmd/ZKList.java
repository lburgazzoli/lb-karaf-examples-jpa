package lb.examples.karaf.zookeeper.client.cmd;

import lb.examples.karaf.cmd.AbstractServiceCommand;
import lb.examples.karaf.zookeeper.client.IZKClient;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

/**
 *
 */
@Command(scope="zk", name="node-list", description="List Create")
public class ZKList extends AbstractServiceCommand<IZKClient> {

    @Argument(index=0, required=true, multiValued=false, name="Path")
    String path;

    @Override
    protected void doExecute(IZKClient service) throws Exception {
        service.list(path);
    }
}
