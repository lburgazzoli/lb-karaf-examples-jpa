package lb.examples.karaf.jpa.utils.cmd;

import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.ServiceReference;

/**
 *
 */
public abstract class AbstractCommand extends OsgiCommandSupport {
    public static final ServiceReference[] EMPTY_REFERENCE_ARRAY = new ServiceReference[0];

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object doExecute() throws Exception {
        doExecuteCommand();
        return null;
    }

    /**
     *
     * @throws Exception
     */
    public abstract void doExecuteCommand() throws Exception;

    /**
     *
     * @param type
     * @return
     * @throws Exception
     */
    protected ServiceReference[] getServiceReferences(Class type) throws Exception {
        return getServiceReferences(type.getName());
    }

    /**
     *
     * @param type
     * @return
     * @throws Exception
     */
    protected ServiceReference[] getServiceReferences(String type) throws Exception {
        ServiceReference[] refs = getBundleContext().getServiceReferences(type,null);
        return refs != null ? refs : EMPTY_REFERENCE_ARRAY;
    }
}
