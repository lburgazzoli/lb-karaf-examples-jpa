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
package com.github.lburgazzoli.examples.karaf;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 */
public class OSGiManagedService implements OSGiServiceLifeCycle,ManagedService {
    public static final Logger LOGGER = LoggerFactory.getLogger(OSGiManagedService.class);

    private final String m_pid;
    private BundleContext m_bundlContext;

    /**
     * c-tor
     */
    public OSGiManagedService(String pid) {
        m_pid = pid;
        m_bundlContext = null;
    }

    /**
     *
     * @param bundelContext
     */
    public void setBundleContext(BundleContext bundelContext) {
        m_bundlContext = bundelContext;
    }

    /**
     *
     */
    @Override
    public void init() {
        try {
            doCreate(getConfiguration());
        } catch(Exception e) {
            LOGGER.warn("Exception",e);
        }
    }

    /**
     *
     */
    @Override
    public void destroy() {
        try {
            doDestroy(getConfiguration());
        } catch(Exception e) {
            LOGGER.warn("Exception",e);
        }
    }

    /**
     *
     * @param dictionary
     * @throws ConfigurationException
     */
    @Override
    public void updated(Dictionary dictionary) throws ConfigurationException {
        if(dictionary != null) {
            try {
                Properties props = getConfiguration(dictionary);

                doDestroy(props);
                doCreate(props);
            } catch(Exception e) {
                throw new ConfigurationException("Updated",e.getMessage(),e);
            }
        }
    }

    /**
     *
     * @return
     */
    protected String getPid() {
        return m_pid;
    }

    /**
     *
     * @return
     */
    protected ConfigurationAdmin getConfigurationAdmin() {
        ServiceReference sr = m_bundlContext != null
            ? m_bundlContext.getServiceReference(ConfigurationAdmin.class)
            : null;

        return sr != null ? (ConfigurationAdmin)m_bundlContext.getService(sr) : null;
    }

    /**
     *
     * @return
     */
    protected Properties getConfiguration() {
        ConfigurationAdmin ca = getConfigurationAdmin();
        Properties props = null;

        if(ca != null) {
            try {
                Configuration configuration = ca.getConfiguration(m_pid);
                if(configuration != null) {
                    props = getConfiguration(configuration.getProperties());
                }
            } catch(Exception e) {
                LOGGER.warn("Exception",e);
            }
        }

        if(props == null) {
            LOGGER.warn("No configuration for {}",m_pid);
        }

        return props;
    }

    /**
     *
     * @param dictionary
     * @return
     */
    protected Properties getConfiguration(Dictionary dictionary) {
        Properties props = null;

        if(dictionary != null) {
            LOGGER.debug("getConfiguration, dictionary={}",dictionary);
            Enumeration keys = dictionary.keys();
            if(keys != null) {
                LOGGER.debug("getConfiguration, keys={}",dictionary);
                props = new Properties();
                while (keys.hasMoreElements()) {
                    Object key = keys.nextElement();
                    Object val = dictionary.get(key);
                    props.put(ObjectUtils.toString(key),ObjectUtils.toString(val, StringUtils.EMPTY));
                }
            }
        }

        if(props == null) {
            LOGGER.warn("No configuration for {}",m_pid);
        }

        return props;
    }

    /**
     *
     * @param properties
     */
    protected void doCreate(Properties properties) throws Exception {
    }

    /**
     *
     * @param properties
     */
    protected void doDestroy(Properties properties) throws Exception {
    }
}
