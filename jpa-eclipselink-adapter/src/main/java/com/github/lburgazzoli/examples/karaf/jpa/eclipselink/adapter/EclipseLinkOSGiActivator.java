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
package com.github.lburgazzoli.examples.karaf.jpa.eclipselink.adapter;

import com.github.lburgazzoli.osgi.OSGiClassLoader;
import com.github.lburgazzoli.osgi.OSGiClassLoaderManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Hashtable;

/**
 *
 */
public class EclipseLinkOSGiActivator implements BundleActivator {
    private static final Logger LOGGER = LoggerFactory.getLogger(EclipseLinkOSGiActivator.class);

    private BundleContext m_context;
    private ServiceRegistration m_serviceReg;
    private final OSGiClassLoader m_cl;
    private final OSGiClassLoaderManager m_clm;

    /**
     * c-tor
     */
    public EclipseLinkOSGiActivator() {
        m_context    = null;
        m_serviceReg = null;
        m_cl         = null;
        m_clm        = null;
    }

    @Override
    public void start(BundleContext context) throws Exception {
        m_context = context;

        Hashtable<String,String> props = new Hashtable<String, String>();
        props.put(
            "javax.persistence.provider",
            org.eclipse.persistence.jpa.PersistenceProvider.class.getName());
        props.put(
            javax.persistence.spi.PersistenceProvider.class.getName(),
            org.eclipse.persistence.jpa.PersistenceProvider.class.getName());
        props.put(
            "javax.persistence.PersistenceProvider",
            org.eclipse.persistence.jpa.PersistenceProvider.class.getName());

        m_serviceReg = context.registerService(
            javax.persistence.spi.PersistenceProvider.class.getName(),
            new EclipseLinkOSGiPersistenceProvider(),
            props);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (m_serviceReg != null) {
            m_serviceReg.unregister();
            m_serviceReg = null;
        }

        m_context = null;
    }
}
