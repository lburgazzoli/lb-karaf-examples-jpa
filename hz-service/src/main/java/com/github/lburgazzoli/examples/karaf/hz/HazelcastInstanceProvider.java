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
package com.github.lburgazzoli.examples.karaf.hz;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class HazelcastInstanceProvider implements IHazelcastInstanceProvider {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(HazelcastInstanceProvider.class);

    private HazelcastInstance m_instance;
    private final Config m_config;

    /**
     * c-tor
     *
     * @param config
     */
    public HazelcastInstanceProvider(Config config) {
        m_config = config;
    }


    // *************************************************************************
    //
    // *************************************************************************

    public void init() {
        if(m_instance == null) {
            m_instance = Hazelcast.newHazelcastInstance(m_config);
            LOGGER.debug("Instance created: {}", m_instance);
        }
    }

    public void destroy() {
        if(m_instance != null) {
            LOGGER.debug("Destroy instance: {}", m_instance);

            m_instance.getLifecycleService().shutdown();
            m_instance = null;
        }
    }

    // *************************************************************************
    // IHazelcastInstanceProvider
    // *************************************************************************

    @Override
    public HazelcastInstance getInstance() {
        return m_instance;
    }
}
