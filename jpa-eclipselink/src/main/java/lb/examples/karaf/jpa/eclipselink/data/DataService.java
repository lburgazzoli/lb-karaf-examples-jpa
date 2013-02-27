package lb.examples.karaf.jpa.eclipselink.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;

/**
 *
 */
public class DataService implements IDataService {
    private EntityManagerFactory emf;

    public DataService() {
        this.emf = null;
    }

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Collection<Item> getAll() {
        EntityManager    em = emf.createEntityManager();
        Collection<Item> ret = em.createQuery("select i from Item i", Item.class).getResultList();

        em.close();

        return ret;
    }

    @Override
    public void add(Item item) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
    }
}
