package lb.examples.karaf.jpa.eclipselink.data;

import java.util.Collection;

/**
 *
 */
public interface IDataService {
    Collection<Item> getAll();
}
