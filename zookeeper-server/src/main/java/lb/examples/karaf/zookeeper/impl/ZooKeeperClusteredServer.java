package lb.examples.karaf.zookeeper.impl;

import lb.examples.karaf.zookeeper.IZKServer;
import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 *
 */
public class ZooKeeperClusteredServer implements IZKServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperClusteredServer.class);

    private QuorumPeer m_server;
    private NIOServerCnxnFactory m_cnxnFactory;

    /**
     * c-tor
     */
    public ZooKeeperClusteredServer() {
        m_server = null;
        m_cnxnFactory = null;
    }

    /**
     *
     * @param config
     */
    public void init(QuorumPeerConfig config) throws Exception {
        m_cnxnFactory= new NIOServerCnxnFactory();
        m_cnxnFactory.configure(config.getClientPortAddress(), config.getMaxClientCnxns());

        m_server = new QuorumPeer();
        m_server.setClientPortAddress(config.getClientPortAddress());
        m_server.setQuorumPeers(config.getServers());
        m_server.setElectionType(config.getElectionAlg());
        m_server.setMyid(config.getServerId());
        m_server.setTickTime(config.getTickTime());
        m_server.setMinSessionTimeout(config.getMinSessionTimeout());
        m_server.setMaxSessionTimeout(config.getMaxSessionTimeout());
        m_server.setInitLimit(config.getInitLimit());
        m_server.setSyncLimit(config.getSyncLimit());
        m_server.setQuorumVerifier(config.getQuorumVerifier());
        m_server.setCnxnFactory(m_cnxnFactory);
        m_server.setZKDatabase(new ZKDatabase(m_server.getTxnFactory()));
        m_server.setLearnerType(config.getPeerType());
        m_server.setTxnFactory(new FileTxnSnapLog(
            new File(config.getDataLogDir()),
            new File(config.getDataDir())));
    }

    /**
     *
     * @throws Exception
     */
    public void start() throws Exception {
        try {
            LOGGER.debug("Starting QuorumPeer {}", m_server.getMyid());
            m_server.start();
            LOGGER.debug("Started QuorumPeer {}", m_server.getMyid());
        } catch (Exception e) {
            LOGGER.warn("Failed to start QuorumPeer {}, reason: {} ", m_server.getMyid(), e.getMessage());
            m_server.shutdown();
            throw e;
        }
    }

    @Override
    public void destroy() throws Exception {
        m_server.shutdown();
        m_server.join();
    }

    @Override
    public String getState() {
        return m_server.getServerState();
    }
}
