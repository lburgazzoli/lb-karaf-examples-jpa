/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.examples.karaf.zookeeper.server.impl;

import com.github.lburgazzoli.examples.karaf.zookeeper.server.IZKServer;
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
        LOGGER.debug("destroying ...");
        m_cnxnFactory.shutdown();
        m_cnxnFactory.join();
        LOGGER.debug("... destroyed");
    }

    @Override
    public String getState() {
        return m_server.getState();
    }
}
