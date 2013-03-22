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
package lb.examples.karaf.zookeeper.client;

import com.google.common.collect.Lists;
import com.netflix.curator.RetryPolicy;
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *
 */
public class ZKClient implements IZKClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZKClient.class);

    private CuratorFrameworkFactory.Builder m_builder;
    private CuratorFramework m_framework;

    /**
     * c-tor
     */
    public ZKClient() {
        m_framework = null;
        m_builder = CuratorFrameworkFactory.builder();
    }

    /**
     *
     */
    @Override
    public void init() {
        if(m_framework != null) {
            m_framework.close();
            m_framework = null;
        }

        m_framework = m_builder.build();
    }

    /**
     *
     */
    @Override
    public void destroy() {
        if(m_framework != null) {
            m_framework.close();
            m_framework = null;
        }
    }

    /**
     *
     * @param connectStrin
     */
    public void setConenctString(String connectStrin) {
        m_builder.connectString(connectStrin);
    }

    /**
     *
     * @param retryPolicy
     */
    public void setRetryPolicy(RetryPolicy retryPolicy) {
        m_builder.retryPolicy(retryPolicy);
    }

    /**
     *
     * @param connectionTimeoutMs
     */
    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        m_builder.connectionTimeoutMs(connectionTimeoutMs);
    }

    /**
     *
     * @param sessionTimeoutMs
     */
    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        m_builder.sessionTimeoutMs(sessionTimeoutMs);
    }

    @Override
    public void create(String path) throws Exception {
        create(path,null);
    }

    @Override
    public void create(String path,String data) throws Exception {
        if(m_framework != null && StringUtils.isNotBlank(path)) {
            m_framework.create().forPath(
                path,
                StringUtils.isNotBlank(data) ? data.getBytes() : ArrayUtils.EMPTY_BYTE_ARRAY);
        }
    }

    @Override
    public void delete(String path) throws Exception {
        if(m_framework != null && StringUtils.isNotBlank(path)) {
            m_framework.delete().forPath(path);
        }
    }

    @Override
    public List<String> list(String path) throws Exception {
        if(m_framework != null && StringUtils.isNotBlank(path)) {
            return m_framework.getChildren().forPath(path);
        }

        return Lists.newArrayList();
    }
}

