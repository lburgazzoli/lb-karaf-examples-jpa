package lb.examples.karaf.jpa.utils.cmd;

import lb.examples.karaf.jpa.utils.ShellTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 *
 */
@Command(scope = "jpa", name = "provider-list", description = "List JPA Providers")
public class JpaProviderList extends OsgiCommandSupport {
    private BundleContext bundleContext;

    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    @Override
    public Object doExecute() throws Exception {
        ShellTable table = new ShellTable("BundleID","BundleName","PersistenceProvider");

        for(Bundle bundle : bundleContext.getBundles()) {
            ServiceReference[] srs = bundle.getRegisteredServices();
            if(srs != null) {
                for(ServiceReference sr : srs) {
                    String providerClass = (String)sr.getProperty("javax.persistence.provider");
                    if(StringUtils.isNotBlank(providerClass)) {
                        table.addRow(
                            bundle.getBundleId(),
                            bundle.getSymbolicName(),
                            providerClass);
                    }
                }
            }
        }

        table.print();

        return null;
    }
}
