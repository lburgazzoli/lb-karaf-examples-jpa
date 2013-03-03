package lb.examples.karaf.jpa.utils.cmd;

import lb.examples.karaf.jpa.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.gogo.commands.Command;
import org.osgi.framework.ServiceReference;

/**
 *
 */
@Command(scope = "jpa", name = "provider-list", description = "List JPA Providers")
public class JpaProviderList extends AbstractTabularCommand {


    public JpaProviderList() {
        super("BundleID","BundleName","PersistenceProvider");
    }

    @Override
    public void doExecuteCommand() throws Exception {
        for(ServiceReference sr : getServiceReferences(Constants.JPA_PP_CLASS)) {
            String providerClass = (String)sr.getProperty(Constants.JPA_PP_SVCP);
            if(StringUtils.isNotBlank(providerClass)) {
                addRow(
                    sr.getBundle().getBundleId(),
                    sr.getBundle().getSymbolicName(),
                    providerClass);
            }
        }
    }
}
