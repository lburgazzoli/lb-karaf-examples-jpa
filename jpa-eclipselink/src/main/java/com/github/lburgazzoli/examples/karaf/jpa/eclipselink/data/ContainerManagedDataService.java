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
package com.github.lburgazzoli.examples.karaf.jpa.eclipselink.data;

import com.github.lburgazzoli.examples.karaf.jpa.commons.data.IDataService;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 *
 */
public class ContainerManagedDataService implements IDataService<Item> {
    private EntityManager em;

    /**
     * c-tor
     */
    public ContainerManagedDataService() {
        this.em = null;
    }

    /**
     * @param em the EntityManager
     */
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Collection<Item> getAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    @Override
    public void add(Item item) {
        em.persist(item);
        em.flush();
    }
}
