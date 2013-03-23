package lb.examples.karaf.zookeeper.client.cmd;

import lb.examples.karaf.cmd.AbstractServiceCommand;
import lb.examples.karaf.zookeeper.client.IZKClient;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

/**
 *
 */
@Command(scope="zk", name="node-data-get", description="Get Data")
public class ZKDataGet extends AbstractServiceCommand<IZKClient> {

    @Argument(index=0, required=true, multiValued=false, name="Path")
    String path;

    @Override
    protected void doExecute(IZKClient service) throws Exception {
        System.out.println("<" + service.data(path) + ">");
    }
}
