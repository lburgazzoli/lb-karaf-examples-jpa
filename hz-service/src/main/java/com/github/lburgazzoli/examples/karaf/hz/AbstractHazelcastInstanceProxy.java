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
package com.github.lburgazzoli.examples.karaf.hz;

import com.github.lburgazzoli.osgi.BundleContextAware;
import com.hazelcast.core.HazelcastInstance;
import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public abstract class AbstractHazelcastInstanceProxy extends BundleContextAware implements IHazelcastInstanceProxy {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(AbstractHazelcastInstanceProxy.class);

    private final IHazelcastInstanceProvider m_instanceProvider;
    private final String m_objectPrefix;

    /**
     * c-tor
     *
     * @param bundleContext
     * @param instanceProvider
     */
    public AbstractHazelcastInstanceProxy(
        BundleContext bundleContext, IHazelcastInstanceProvider instanceProvider, String objectPrefix) {
        super(bundleContext);
        m_instanceProvider = instanceProvider;
        m_objectPrefix = objectPrefix;
    }


    /**
     *
     * @param name
     * @return
     */
    protected String getDistributedObjectName(String name) {
        return StringUtils.isEmpty(m_objectPrefix) ? name : m_objectPrefix + ":" + name;
    }

    // *************************************************************************
    // IHazelcastInstanceProxy
    // *************************************************************************

    @Override
    public HazelcastInstance getInstance() {
        return m_instanceProvider.getInstance();
    }

    @Override
    public boolean hasInstance() {
        return getInstance() != null;
    }
}
