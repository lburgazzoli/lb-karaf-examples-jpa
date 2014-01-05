/*
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
package com.github.lburgazzoli.examples.karaf.jpa.hibernate.noaries.data;

import com.github.lburgazzoli.karaf.common.ILifeCycle;
import com.google.common.collect.Lists;
import com.github.lburgazzoli.examples.karaf.jpa.commons.data.IDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.util.Collection;
import java.util.Properties;

/**
 *
 */
public class ApplicationManagedDataService implements ILifeCycle, IDataService<Item> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationManagedDataService.class);

    private EntityManagerFactory emf;
    private PersistenceProvider pp;
    private String unitName;
    private Properties props;

    /**
     * c-tor
     */
    public ApplicationManagedDataService() {
        this.emf = null;
        this.pp = null;
        this.unitName = null;
        this.props = new Properties();
    }

    /**
     *
     * @param pp
     */
    public void setPersistenceProvider(PersistenceProvider pp) {
        this.pp = pp;
    }

    /**
     *
     * @param unitName
     */
    public void setPersistenceUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     *
     * @param props
     */
    public void setPersistenceUnitProperties(Properties props) {
        this.props.clear();
        this.props.putAll(props);
    }

    // *************************************************************************
    // ILifecycle
    // *************************************************************************

    @Override
    public void init() {
        if(this.pp != null) {
            this.emf = pp.createEntityManagerFactory(this.unitName, this.props);
        }
    }

    @Override
    public void destroy() {
        if(this.emf != null) {
            this.emf.close();
            this.emf = null;
        }
    }

    // *************************************************************************
    // IDataService
    // *************************************************************************

    @Override
    public Collection<Item> getAll() {
        EntityManager    em  = emf.createEntityManager();
        Collection<Item> ret = Lists.newArrayList();

        try {
            ret = em.createQuery("select i from Item i", Item.class).getResultList();
        } finally {
            em.close();
        }

        return ret;
    }

    @Override
    public void add(Item item) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } catch(Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Exception: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
