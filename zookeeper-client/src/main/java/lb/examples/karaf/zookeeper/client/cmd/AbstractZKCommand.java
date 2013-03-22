package lb.examples.karaf.zookeeper.client.cmd;

import lb.examples.karaf.zookeeper.client.IZKClient;
import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 *
 */
public abstract class AbstractZKCommand extends OsgiCommandSupport {

    private IZKClient m_zkClient;

    /**
     * c-tor
     */
    public AbstractZKCommand() {
        m_zkClient = null;
    }

    /**
     *
     * @param zkClient
     */
    public void setZkClient(IZKClient zkClient) {
        m_zkClient = zkClient;
    }

    /**
     *
     * @return
     */
    public IZKClient getZkClient() {
        return m_zkClient;
    }

    @Override
    protected Object doExecute() throws Exception {
        doExecute(m_zkClient);
        return null;
    }

    /**
     *
     * @param zkClient
     * @throws Exception
     */
    protected abstract void doExecute(IZKClient zkClient) throws Exception;
}
