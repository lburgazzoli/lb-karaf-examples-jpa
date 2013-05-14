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
package com.github.lburgazzoli.examples.karaf.zookeeper.client;

import com.google.common.collect.Lists;
import com.netflix.curator.framework.CuratorFramework;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 *
 */
public class ZKClient extends ZKClientBuilder implements IZKClient {
    private static final List<String> EMPTY_LIST_STRING = Lists.newArrayList();

    private CuratorFramework m_framework;

    /**
     * c-tor
     */
    public ZKClient() {
        m_framework = null;
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

        m_framework = build();
        m_framework.start();
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

    // *************************************************************************
    // IZKClient
    // *************************************************************************

    @Override
    public void create(String path) throws Exception {
        create(path, null);
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
        return list(path,false);
    }

    @Override
    public List<String> list(String path,boolean recursive) throws Exception {
        if(m_framework != null && StringUtils.isNotBlank(path)) {
            return m_framework.getChildren().forPath(path);
        }

        return EMPTY_LIST_STRING;
    }

    @Override
    public byte[] data(String path) throws Exception {
        if(m_framework != null && StringUtils.isNotBlank(path))  {
            return m_framework.getData().forPath(path);
        }

        return ArrayUtils.EMPTY_BYTE_ARRAY;
    }
}

