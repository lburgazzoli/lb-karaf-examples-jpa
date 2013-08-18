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
package com.github.lburgazzoli.examples.karaf.jpa.commons;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.ProviderUtil;
import java.util.Map;

/**
 *
 */
public class PersistenceProviderProxy implements PersistenceProvider {

    private final PersistenceProvider m_delegatedProvider;

    /**
     * c-tor
     * @param delegatedProvider
     */
    public PersistenceProviderProxy(PersistenceProvider delegatedProvider) {
        m_delegatedProvider = delegatedProvider;
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public EntityManagerFactory createEntityManagerFactory(String name, Map map) {
        return m_delegatedProvider.createEntityManagerFactory(name,map);
    }

    @Override
    public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map map) {
        return m_delegatedProvider.createContainerEntityManagerFactory(info,map);
    }

    @Override
    public ProviderUtil getProviderUtil() {
        return m_delegatedProvider.getProviderUtil();
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @return
     */
    protected PersistenceProvider getDelegate() {
        return m_delegatedProvider;
    }
}
