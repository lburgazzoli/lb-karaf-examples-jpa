package com.github.lburgazzoli.osgi.hazelcast;

import com.hazelcast.config.Config;

/**
 *
 */
public class HazelcastLocalConfig extends Config {

    /**
     * c-tor
     */
    public HazelcastLocalConfig() {
        this(null);
    }

    /**
     * c-tor
     */
    public HazelcastLocalConfig(ClassLoader classLoader) {
        super.setProperty("hazelcast.logging.type","slf4j");
        super.getNetworkConfig().setPortAutoIncrement(true);
        super.getNetworkConfig().getInterfaces().setEnabled(false);
        super.getNetworkConfig().getJoin().getAwsConfig().setEnabled(false);
        super.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        super.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(false);

        if(classLoader != null) {
            super.setClassLoader(classLoader);
        }
    }
}
