package lb.examples.karaf.zookeeper;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

import java.util.Dictionary;

/**
 *
 */
public class ZooKeeperServer implements ManagedService {
    @Override
    public void updated(Dictionary<String, ?> properties) throws ConfigurationException {
        if(properties != null) {
        }
    }
}
