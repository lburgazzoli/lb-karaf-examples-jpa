package lb.examples.karaf.jpa.eclipselink.adapter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

/**
 *
 */
public class Activator  implements BundleActivator {

    private static BundleContext context = null;
    private static ServiceRegistration serviceReg = null;

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;

        Hashtable<String,String> props = new Hashtable<String, String>();
        props.put(
            "javax.persistence.provider",
            "org.eclipse.persistence.jpa.PersistenceProvider");
        props.put(
            "javax.persistence.spi.PersistenceProvider",
            "org.eclipse.persistence.jpa.PersistenceProvider");
        props.put(
            "javax.persistence.PersistenceProvider",
            "org.eclipse.persistence.jpa.PersistenceProvider");

        serviceReg = context.registerService(
            "javax.persistence.spi.PersistenceProvider",
            new org.eclipse.persistence.jpa.PersistenceProvider(),
            props);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (this.serviceReg != null) {
            this.serviceReg.unregister();
            this.serviceReg = null;
        }

        this.context = null;
    }
}
