package lb.examples.karaf.jpa.openjpa.data;

import com.google.common.collect.Lists;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;

/**
 *
 */
public class ApplicationManagedDataService implements IDataService {
    private EntityManagerFactory emf;

    public ApplicationManagedDataService() {
        this.emf = null;
    }

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Collection<Item> getAll() {
        EntityManager    em  = emf.createEntityManager();
        Collection<Item> ret = Lists.newArrayList();

        try {
            ret = em.createQuery("select i from Item i", Item.class).getResultList();
        } finally {
            em.close();
        }

        return ret;
    }

    @Override
    public void add(Item item) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } catch(Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Exception: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
