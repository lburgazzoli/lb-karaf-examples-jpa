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
package com.github.lburgazzoli.examples.karaf.jpa.commons;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * c-tor
 */
public class PersistenceUnitInfoProxy implements PersistenceUnitInfo {

    private final PersistenceUnitInfo m_delegatedUnitInfo;

    /**
     * c-tor
     *
     * @param delegatedUnitInfo
     */
    public PersistenceUnitInfoProxy(PersistenceUnitInfo delegatedUnitInfo) {
        m_delegatedUnitInfo = delegatedUnitInfo;
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public String getPersistenceUnitName() {
        return m_delegatedUnitInfo.getPersistenceUnitName();
    }

    @Override
    public String getPersistenceProviderClassName() {
        return m_delegatedUnitInfo.getPersistenceProviderClassName();
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return m_delegatedUnitInfo.getTransactionType();
    }

    @Override
    public DataSource getJtaDataSource() {
        return m_delegatedUnitInfo.getJtaDataSource();
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return m_delegatedUnitInfo.getNonJtaDataSource();
    }

    @Override
    public List<String> getMappingFileNames() {
        return m_delegatedUnitInfo.getMappingFileNames();
    }

    @Override
    public List<URL> getJarFileUrls() {
        return m_delegatedUnitInfo.getJarFileUrls();
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return m_delegatedUnitInfo.getPersistenceUnitRootUrl();
    }

    @Override
    public List<String> getManagedClassNames() {
        return m_delegatedUnitInfo.getManagedClassNames();
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return m_delegatedUnitInfo.excludeUnlistedClasses();
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return m_delegatedUnitInfo.getSharedCacheMode();
    }

    @Override
    public ValidationMode getValidationMode() {
        return m_delegatedUnitInfo.getValidationMode();
    }

    @Override
    public Properties getProperties() {
        return m_delegatedUnitInfo.getProperties();
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return m_delegatedUnitInfo.getPersistenceXMLSchemaVersion();
    }

    @Override
    public ClassLoader getClassLoader() {
        return m_delegatedUnitInfo.getClassLoader();
    }

    @Override
    public void addTransformer(ClassTransformer transformer) {
        m_delegatedUnitInfo.addTransformer(transformer);
    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return m_delegatedUnitInfo.getNewTempClassLoader();
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @return
     */
    protected PersistenceUnitInfo getDelegate() {
        return m_delegatedUnitInfo;
    }
}
