package com.github.lburgazzoli.osgi.hazelcast;

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
