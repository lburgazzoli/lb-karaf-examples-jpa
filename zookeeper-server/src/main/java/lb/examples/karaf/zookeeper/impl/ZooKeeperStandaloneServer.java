package lb.examples.karaf.zookeeper.impl;

import lb.examples.karaf.zookeeper.IZKServer;
import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 *
 */
public class ZooKeeperStandaloneServer implements IZKServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperStandaloneServer.class);

    private ZooKeeperServer m_server;
    private NIOServerCnxnFactory m_cnxnFactory;

    /**
     * c-tor
     */
    public ZooKeeperStandaloneServer() {
        m_server = null;
        m_cnxnFactory = null;
    }

    /**
     *
     * @param config
     */
    public void init(QuorumPeerConfig config) throws Exception {
        m_cnxnFactory = new NIOServerCnxnFactory();
        m_cnxnFactory.configure(config.getClientPortAddress(), config.getMaxClientCnxns());

        m_server = new ZooKeeperServer();
        m_server.setTickTime(config.getTickTime());
        m_server.setMinSessionTimeout(config.getMinSessionTimeout());
        m_server.setMaxSessionTimeout(config.getMaxSessionTimeout());
        m_server.setTxnLogFactory(new FileTxnSnapLog(
            new File(config.getDataLogDir()),
            new File(config.getDataDir())));
    }

    /**
     *
     * @throws Exception
     */
    public void start() throws Exception {
        try {
            LOGGER.debug("Starting ZooKeeper server");
            m_cnxnFactory.startup(m_server);
            LOGGER.debug("Started ZooKeeper server");
        } catch (Exception e) {
            LOGGER.warn("Failed to start ZooKeeper server, reason: {}", e);
            m_cnxnFactory.shutdown();
            throw e;
        }
    }

    @Override
    public void destroy() throws Exception {
        m_cnxnFactory.shutdown();
        m_cnxnFactory.join();
    }

    @Override
    public String getState() {
        return m_server.getState();
    }
}
