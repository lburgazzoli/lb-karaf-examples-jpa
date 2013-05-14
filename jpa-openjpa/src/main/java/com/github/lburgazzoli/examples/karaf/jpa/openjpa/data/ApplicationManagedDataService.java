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
package com.github.lburgazzoli.examples.karaf.jpa.openjpa.data;

import com.google.common.collect.Lists;
import com.github.lburgazzoli.examples.karaf.jpa.commons.data.IDataService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;

/**
 *
 */
public class ApplicationManagedDataService implements IDataService<Item> {
    private EntityManagerFactory emf;

    public ApplicationManagedDataService() {
        this.emf = null;
    }

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

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
