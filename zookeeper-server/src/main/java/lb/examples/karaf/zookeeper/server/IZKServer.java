package lb.examples.karaf.zookeeper.server.cmd;

import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

/**
 *
 */
public interface IZKServer {

    /**
     *
     * @param config
     */
    public void init(QuorumPeerConfig config) throws Exception;

    /**
     *
     * @throws Exception
     */
    public void start() throws Exception;

    /**
     *
     * @throws Exception
     */
    public void destroy() throws Exception;

    /**
     *
     * @return
     */
    public String getState();
}
