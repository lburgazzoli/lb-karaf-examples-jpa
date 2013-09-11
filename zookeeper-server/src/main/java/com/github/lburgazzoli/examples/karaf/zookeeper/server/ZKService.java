/**
 *
 * Copyright 2013 lb
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.examples.karaf.zookeeper.server;

import com.github.lburgazzoli.examples.karaf.zookeeper.server.impl.ZooKeeperClusteredServer;
import com.github.lburgazzoli.examples.karaf.zookeeper.server.impl.ZooKeeperStandaloneServer;
import com.github.lburgazzoli.karaf.common.cmd.AbstractManagedService;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.util.Properties;

/**
 *
 */
public class ZKService extends AbstractManagedService implements IZKService {

    private IZKServer m_server;

    /**
     * c-tor
     *
     * @param pid
     */
    public ZKService(String pid) {
        super(pid);

        m_server = null;
    }

    @Override
    protected void doCreate(Properties properties) throws Exception {
        if(properties != null && m_server == null) {
            QuorumPeerConfig config = new QuorumPeerConfig();
            config.parseProperties(properties);
            if(!config.getServers().isEmpty()) {
                ZKUtils.createIdFile(properties);

                m_server = new ZooKeeperClusteredServer();
                m_server.init(config);
                m_server.start();
            } else {
                m_server = new ZooKeeperStandaloneServer();
                m_server.init(config);
                m_server.start();
            }
        }
    }

    @Override
    protected void doDestroy(Properties properties) throws Exception {
        if(properties != null && m_server != null) {
            m_server.destroy();
            m_server = null;
        }
    }
}
