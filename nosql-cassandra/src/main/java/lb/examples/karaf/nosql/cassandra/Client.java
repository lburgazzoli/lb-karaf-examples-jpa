package lb.examples.karaf.nosql.cassandra;

import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.commons.beanutils.BeanUtils;
import org.scale7.cassandra.pelops.Bytes;
import org.scale7.cassandra.pelops.Cluster;
import org.scale7.cassandra.pelops.Mutator;
import org.scale7.cassandra.pelops.Pelops;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.lang.annotation.Annotation;

/**
 *
 */
public class Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    private Cluster m_cluster;
    private String m_keySpace;
    private String m_poolName;

    /**
     * c-tor
     */
    public Client() {
        m_cluster = null;
        m_keySpace = null;
        m_poolName = null;
    }

    /**
     *
     * @param cluster
     */
    public void setCluster(Cluster cluster) {
        m_cluster = cluster;
    }

    /**
     *
     * @param keySpace
     */
    public void setKeySpace(String keySpace) {
        m_keySpace = keySpace;
    }

    /**
     *
     * @param poolName
     */
    public void setPoolName(String poolName) {
        m_poolName = poolName;
    }

    /**
     *
     */
    public void init() {
        if(m_cluster != null && m_keySpace != null) {
            Pelops.addPool(m_poolName, m_cluster, m_keySpace);
        }
    }

    /**
     *
     */
    public void destroy() {
        if(m_cluster != null) {
            Pelops.shutdown();
        }
    }

    public void persist(Object data) {
        Class<?> clazz = data.getClass();
        Entity entity = clazz.getAnnotation(Entity.class);
        Table  table  = clazz.getAnnotation(Table.class);

        if(entity != null && table != null) {
            for(Annotation annotation : clazz.getDeclaredAnnotations()) {
                if(Column.class == annotation.getClass()) {
                    Column column = (Column)annotation;
                    String name = column.name();

                    org.apache.cassandra.thrift.Column ccol =
                        new org.apache.cassandra.thrift.Column();

                    ccol.setName(name.getBytes());
                    ccol.setValue("".getBytes());
                    ccol.setTimestamp(System.currentTimeMillis());
                }
            }

            Mutator mutator = Pelops.createMutator(m_poolName);

            mutator.writeColumns(
                table.name(),
                "",
                mutator.newColumnList(
                    mutator.newColumn("name", "Dan"),
                    mutator.newColumn("age", Bytes.fromInt(33))
                )
            );

            mutator.execute(ConsistencyLevel.ONE);
        }
    }
}
