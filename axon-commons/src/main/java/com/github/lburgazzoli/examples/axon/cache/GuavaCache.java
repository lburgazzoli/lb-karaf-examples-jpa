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

package com.github.lburgazzoli.examples.axon.cache;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheEntry;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheListener;
import net.sf.jsr107cache.CacheStatistics;
import org.axonframework.domain.AggregateRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class GuavaCache implements Cache {
    private final static Logger LOGGER = LoggerFactory.getLogger(GuavaCache.class);

    private final com.google.common.cache.Cache<Object,Object> m_cache;

    /**
     *
     * @param cache
     */
    public GuavaCache(com.google.common.cache.Cache<Object,Object> cache) {
        m_cache = cache;
    }

    @Override
    public boolean containsKey(Object key) {
        return m_cache.getIfPresent(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return m_cache.asMap().containsValue(value);
    }

    @Override
    public Set keySet() {
        return m_cache.asMap().keySet();
    }

    @Override
    public Set entrySet() {
        return m_cache.asMap().entrySet();
    }

    @Override
    public boolean isEmpty() {
        return m_cache.size() == 0;
    }

    @Override
    public void putAll(Map t) {
        m_cache.putAll(t);
    }

    @Override
    public int size() {
        return (int)m_cache.size();
    }

    @Override
    public Collection values() {
        return m_cache.asMap().values();
    }

    @Override
    public Object get(Object key) {
        Object value =  m_cache.getIfPresent(key);
        if(value != null) {
            if(value instanceof AggregateRoot) {
                AggregateRoot ar = (AggregateRoot)value;
                LOGGER.debug("Cache.get : id={}, version={}",
                    ar.getIdentifier(),ar.getVersion());
            }
        }

        return value;
    }

    @Override
    public Map getAll(Collection keys) throws CacheException {
        return m_cache.getAllPresent(keys);
    }

    @Override
    public void load(Object key) throws CacheException {
        throw new RuntimeException("load - NotImplemented");
    }

    @Override
    public void loadAll(Collection keys) throws CacheException {
        throw new RuntimeException("loadAll - NotImplemented");
    }

    @Override
    public Object peek(Object key) {
        return m_cache.getIfPresent(key);
    }

    @Override
    public Object put(Object key, Object value) {
        Object old = m_cache.getIfPresent(key);

        if(value instanceof AggregateRoot) {
            AggregateRoot ar = (AggregateRoot)value;
            LOGGER.debug("Cache.put : id={}, version={}",
                ar.getIdentifier(),ar.getVersion());
        }

        m_cache.put(key,value);

        return old;
    }

    @Override
    public CacheEntry getCacheEntry(Object key) {
        return null;
    }

    @Override
    public CacheStatistics getCacheStatistics() {
        return null;
    }

    @Override
    public Object remove(Object key) {
        m_cache.invalidate(key);
        return null;
    }

    @Override
    public void clear() {
        m_cache.cleanUp();
    }

    @Override
    public void evict() {
        throw new RuntimeException("evict - NotImplemented");
    }

    @Override
    public void addListener(CacheListener listener) {
        throw new RuntimeException("addListener - NotImplemented");
    }

    @Override
    public void removeListener(CacheListener listener) {
        throw new RuntimeException("removeListener - NotImplemented");
    }
}
