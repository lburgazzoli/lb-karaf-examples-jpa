package lb.examples.karaf.zookeeper.server.cmd;

import lb.examples.karaf.zookeeper.IZKService;
import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 *
 */
public abstract class AbstractZKCommand extends OsgiCommandSupport {

    private IZKService m_zkService;

    /**
     * c-tor
     */
    public AbstractZKCommand() {
        m_zkService = null;
    }

    /**
     *
     * @param zkService
     */
    public void setZkService(IZKService zkService) {
        m_zkService = zkService;
    }

    /**
     *
     * @return
     */
    public IZKService getZkService() {
        return m_zkService;
    }

    @Override
    protected Object doExecute() throws Exception {
        doExecute(m_zkService);
        return null;
    }

    /**
     *
     * @param zkService
     * @throws Exception
     */
    protected abstract void doExecute(IZKService zkService) throws Exception;
}
