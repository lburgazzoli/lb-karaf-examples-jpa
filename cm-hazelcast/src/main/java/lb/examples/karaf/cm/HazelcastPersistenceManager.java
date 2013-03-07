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
import com.hazelcast.client.ClientConfig;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.cm.PersistenceManager;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 *
 */
public class HazelcastPersistenceManager implements PersistenceManager, EntryListener<String,String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HazelcastPersistenceManager.class);

    private HazelcastInstance m_client;
    private IMap<String,String> m_data;

    /**
     * c-tor
     */
    public HazelcastPersistenceManager() {
        m_client = null;
        m_data = null;
    }

    //
    // Bean Lifecycle
    //

    /**
     *
     */
    public void init() {
        ClientConfig config = new ClientConfig();
        config.getGroupConfig().setName("dev").setPassword("dev-pass");
        config.addAddress("127.0.0.1:5701");

        m_client = HazelcastClient.newHazelcastClient(config);

        m_data = m_client.getMap("karaf-cm");
        m_data.addEntryListener(this,true);
        m_data.put("lb.examples.karaf.cm.hz1",newWithPid("lb.examples.karaf.cm.hz1"));
        m_data.put("lb.examples.karaf.cm.hz2",newWithPid("lb.examples.karaf.cm.hz2"));
    }

    /**
     *
     */
    public void destroy() {
        m_data.removeEntryListener(this);
        m_client.getLifecycleService().shutdown();

        m_data = null;
        m_client = null;
    }

    //
    // PersistenceManager
    //

    @Override
    public boolean exists(String pid) {
        LOGGER.debug("exists {} ({})",pid,m_data.containsKey(pid));
        return StringUtils.startsWith(pid,"lb.examples.karaf") ? m_data.containsKey(pid) : false;
    }

    @Override
    public Dictionary load(String pid) throws IOException {
        Hashtable result = new Hashtable();
        LOGGER.debug("load {}",pid);
        if(m_data.containsKey(pid)) {
            Map<String,Object> data = (Map<String,Object>)JSONValue.parse(m_data.get(pid));
            result = new Hashtable(data);
        }
        return result;
    }

    @Override
    public Enumeration getDictionaries() throws IOException {
        LOGGER.debug("getDictionaries");
        Vector<Dictionary> values = new Vector<Dictionary>();
        Collection<String> dataValue = m_data.values();
        for(String info : dataValue) {
            Map<String,Object> data = (Map<String,Object>)JSONValue.parse(info);
            values.add(new Hashtable(data));
        }

        return values.elements();
    }

    @Override
    public void store(String pid, Dictionary properties) throws IOException {
        LOGGER.debug("store {}/<{}>",pid,properties);

        if(StringUtils.startsWith(pid,"lb.examples.karaf")) {
            Map<String,Object> data = Maps.newHashMap();
            Enumeration it = properties.keys();
            while(it.hasMoreElements()) {
                String key = (String)it.nextElement();
                data.put(key,properties.get(key));
                LOGGER.debug("store <{}|{}>",key,properties.get(key));
            }

            if(!data.isEmpty()) {
                String json = JSONValue.toJSONString(data);
                LOGGER.debug("store {} => {}",pid,json);
                m_data.put(pid,json);
            }
        }
    }

    @Override
    public void delete(String pid) throws IOException {
        LOGGER.debug("delete {}",pid);
        if(StringUtils.startsWith(pid,"lb.examples.karaf")) {
            m_data.remove(pid);
        }
    }

    //
    // EntryListener<String,HazelcastPersistenceInfo>
    //

    @Override
    public void entryAdded(EntryEvent<String, String> event) {
    }

    @Override
    public void entryRemoved(EntryEvent<String, String> event) {
    }

    @Override
    public void entryUpdated(EntryEvent<String, String> event) {
    }

    @Override
    public void entryEvicted(EntryEvent<String, String> event) {
    }

    //
    // Helpers
    //

    /**
     *
     * @param pid
     * @return
     */
    public static String newWithPid(String pid) {
        Map<String,Object> data = Maps.newHashMap();
        data.put("service.pid", pid);

        return JSONValue.toJSONString(data);
    }
}
