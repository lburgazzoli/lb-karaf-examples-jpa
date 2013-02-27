package lb.examples.karaf.jpa.eclipselink.data;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 *
 */
public class DataService implements IDataService {
    private EntityManager em;

    public DataService() {
        this.em = null;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Collection<Item> getAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
