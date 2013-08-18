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
package com.github.lburgazzoli.examples.karaf.jpa.eclipselink.adapter;

import com.github.lburgazzoli.examples.karaf.jpa.commons.PersistenceProviderProxy;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import java.util.Map;

/**
 *
 */
public class EclipseLinkOSGiPersistenceProvider extends PersistenceProviderProxy {

    /**
     *c-tor
     */
    public EclipseLinkOSGiPersistenceProvider() {
        super(new org.eclipse.persistence.jpa.PersistenceProvider());
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public EntityManagerFactory createEntityManagerFactory(String name, Map map) {
        if (!map.containsKey(PersistenceUnitProperties.LOGGING_LOGGER)) {
            map.put(
                PersistenceUnitProperties.LOGGING_LOGGER,
                EclipseLinkOSGiServerPlatform.class.getName());
        }

        return getDelegate().createEntityManagerFactory(name, map);
    }

    @Override
    public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map map) {
        if (!map.containsKey(PersistenceUnitProperties.LOGGING_LOGGER)) {
            map.put(
                PersistenceUnitProperties.LOGGING_LOGGER,
                EclipseLinkOSGiSessionLogger.class.getName());
        }
        if (!map.containsKey(PersistenceUnitProperties.TARGET_SERVER)) {
            map.put(
                PersistenceUnitProperties.TARGET_SERVER,
                EclipseLinkOSGiServerPlatform.class.getName());
        }

        return getDelegate().createContainerEntityManagerFactory(info,map);
    }
}
