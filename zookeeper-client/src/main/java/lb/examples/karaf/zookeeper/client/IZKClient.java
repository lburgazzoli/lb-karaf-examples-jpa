package lb.examples.karaf.zookeeper.client;

import lb.examples.karaf.OSGiServiceLifeCycle;

import java.util.List;

/**
 *
 */
public interface IZKClient extends OSGiServiceLifeCycle {

    /**
     * @param path
     */
    public void create(String path) throws Exception;

    /**
     *
     * @param path
     * @param data
     */
    public void create(String path,String data) throws Exception;

    /**
     *
     * @param path
     */
    public void delete(String path) throws Exception;

    /**
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws Exception;
}
