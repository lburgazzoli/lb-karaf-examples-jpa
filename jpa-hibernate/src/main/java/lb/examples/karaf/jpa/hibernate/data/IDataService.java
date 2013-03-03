package lb.examples.karaf.jpa.hibernate.data;

import java.util.Collection;

/**
 *
 */
public interface IDataService {
    /**
     *
     * @return
     */
    Collection<Item> getAll();

    /**
     *
      * @param item
     */
    void add(Item item);
}
