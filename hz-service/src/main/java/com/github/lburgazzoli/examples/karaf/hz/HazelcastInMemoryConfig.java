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
