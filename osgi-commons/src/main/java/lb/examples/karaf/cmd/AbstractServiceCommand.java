package lb.examples.karaf.cmd;

import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 *
 */
public abstract class AbstractServiceCommand<T> extends OsgiCommandSupport {

    private T m_service;

    /**
     * c-tor
     */
    public AbstractServiceCommand() {
        m_service = null;
    }

    /**
     *
     * @param service
     */
    public void setService(T service) {
        m_service = service;
    }

    /**
     *
     * @return
     */
    public T getService() {
        return m_service;
    }

    @Override
    protected Object doExecute() throws Exception {
        doExecute(m_service);
        return null;
    }

    /**
     *
     * @param service
     * @throws Exception
     */
    protected abstract void doExecute(T service) throws Exception;
}
