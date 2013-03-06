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
package lb.examples.karaf.cm;

import com.google.common.collect.Maps;
import org.apache.felix.cm.PersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 */
public class InMemoryPersistenceManager implements PersistenceManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryPersistenceManager.class);

    private Map<String,Map<Object,Object>> m_data;

    public InMemoryPersistenceManager() {
        m_data = Maps.newHashMap();
        m_data.put("lb.examples.karaf",new HashMap<Object,Object>() {{
            put("key_1_1","val_1_1");
            put("key_1_2","val_1_2");
        }});
        m_data.put("lb.examples.karaf.jpa",new HashMap<Object,Object>() {{
            put("key_2_1","val_2_1");
            put("key_2_2","val_2_2");
        }});
    }

    @Override
    public boolean exists(String pid) {
        LOGGER.debug("exists {} ({})",pid,m_data.containsKey(pid));
        return m_data.containsKey(pid);
    }

    @Override
    public Dictionary load(String pid) throws IOException {
        LOGGER.debug("load {}",pid);
        if(!m_data.containsKey(pid)) {
            m_data.put(pid,new HashMap<Object,Object>());
        }

        return new Hashtable<Object,Object>(m_data.get(pid));
    }

    @Override
    public Enumeration getDictionaries() throws IOException {
        LOGGER.debug("getDictionaries");
        return Collections.enumeration(m_data.values());
    }

    @Override
    public void store(String pid, Dictionary properties) throws IOException {
        LOGGER.debug("store : {}",pid);
        if(!m_data.containsKey(pid)) {
            Map<Object,Object> ht = Maps.newHashMap();
            Enumeration e = properties.keys();
            while(e.hasMoreElements()){
                Object key = e.nextElement();
                ht.put(key,properties.get(key));
                LOGGER.debug("pid={], k={}, v={}",pid,key,properties.get(key));
            }

            m_data.put(pid,ht);
        }
    }

    @Override
    public void delete(String pid) throws IOException {
        LOGGER.debug("delete : {}",pid);
        m_data.remove(pid);
    }
}
