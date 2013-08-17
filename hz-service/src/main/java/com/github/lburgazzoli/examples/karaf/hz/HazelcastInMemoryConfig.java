package com.github.lburgazzoli.examples.karaf.hz;

import com.hazelcast.config.Config;

/**
 *
 */
public class HazelcastInMemoryConfig extends Config {

    /**
     * c-tor
     */
    public HazelcastInMemoryConfig() {
        this(null);
    }

    /**
     * c-tor
     */
    public HazelcastInMemoryConfig(ClassLoader classLoader) {
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
