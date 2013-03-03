package lb.examples.karaf.jpa.hibernate.cmd;

import lb.examples.karaf.jpa.hibernate.data.ApplicationManagedDataService;
import lb.examples.karaf.jpa.hibernate.data.Item;
import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

/**
 *
 */
@Command(scope = "item", name = "add", description = "Add and item")
public class ItemAddCommand implements Action {
    @Argument(
        index       = 0,
        name        = "Name",
        required    = true,
        description = "Item Name",
        multiValued = false)
    String name;

    @Argument(
        index       = 1,
        name        = "Description",
        required    = true,
        description = "Item Description",
        multiValued = false)
    String description;

    private ApplicationManagedDataService m_applicationManagedDataService;

    public void setApplicationManagedDataService(ApplicationManagedDataService applicationManagedDataService) {
        this.m_applicationManagedDataService = applicationManagedDataService;
    }

    @Override
    public Object execute(CommandSession session) throws Exception {
        m_applicationManagedDataService.add(new Item(name,description));
        return null;
    }
}
