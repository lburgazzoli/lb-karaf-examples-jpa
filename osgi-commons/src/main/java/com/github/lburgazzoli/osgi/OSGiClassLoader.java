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
package com.github.lburgazzoli.osgi;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 *
 */
public class OSGiClassLoader extends ClassLoader {
    public static final Logger LOGGER = LoggerFactory.getLogger(OSGiClassLoader.class);

    private final ConcurrentMap<Long, Bundle> m_bundles;
    private final ConcurrentMap<String,Class<?>> m_classes;
    private final ConcurrentMap<String,URL> m_resources;
    private final List<ClassLoader> m_classLoaders;

    /**
     * c-tor
     */
    public OSGiClassLoader() {
        m_bundles = Maps.newConcurrentMap();
        m_classes = Maps.newConcurrentMap();
        m_resources = Maps.newConcurrentMap();
        m_classLoaders = Lists.newArrayList();
    }

    /**
     *
     * @param bundle
     */
    public void addBundle(Bundle bundle) {
        LOGGER.debug("addBundle: {}",bundle.getSymbolicName());

        m_bundles.put(bundle.getBundleId(), bundle);
        m_classes.clear();
    }

    /**
     *
     * @param bundle
     */
    public void removeBundle(Bundle bundle) {
        LOGGER.debug("removeBundle: {}",bundle.getSymbolicName());

        m_bundles.remove(bundle.getBundleId());
        m_classes.clear();
    }

    /**
     *
     * @param classLoader
     */
    public void addClassLoader(ClassLoader classLoader) {
        m_classLoaders.add(classLoader);
        m_classes.clear();
    }

    /**
     *
     * @param classLoader
     */
    public void removeClassLoader(ClassLoader classLoader) {
        m_classLoaders.remove(classLoader);
        m_classes.clear();
    }

    /**
     *
     */
    public void clear() {
        m_resources.clear();
        m_bundles.clear();
        m_classLoaders.clear();
        m_classes.clear();
    }

    // *************************************************************************
    //
    // *************************************************************************

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        Class<?> clazz = m_classes.get(name);
        if(clazz == null) {
            for(Map.Entry<Long,Bundle> entry : m_bundles.entrySet()) {
                try {
                    clazz = entry.getValue().loadClass(name);
                    m_classes.put(name, clazz);

                    LOGGER.debug("Found class : {} => {}",name,entry.getValue().getSymbolicName());

                    return clazz;
                } catch(ClassNotFoundException e) {
                }
            }

            for(ClassLoader classLoader : m_classLoaders) {
                try {
                    clazz = classLoader.loadClass(name);
                    if(clazz != null) {
                        m_classes.put(name, clazz);
                        return clazz;
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        } else {
            return clazz;
        }

        throw new ClassNotFoundException(name);
    }

    @Override
    public URL getResource(String name) {
        URL url = m_resources.get(name);
        if(url == null) {
            for (Map.Entry<Long,Bundle> entry : m_bundles.entrySet()) {
                url = entry.getValue().getResource(name);
                if(url != null) {
                    m_resources.put(name,url);
                    return url;
                }
            }

            for(ClassLoader classLoader : m_classLoaders) {
                try {
                    url = classLoader.getResource(name);
                    if(url != null) {
                        m_resources.put(name, url);
                        return url;
                    }
                } catch (Exception e) {
                }
            }
        } else {
            return url;
        }

        //TODO: error?
        return null;
    }
}
