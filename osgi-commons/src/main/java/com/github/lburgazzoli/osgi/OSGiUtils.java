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
package com.github.lburgazzoli.osgi;

import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 */
public class OSGiUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(OSGiUtils.class);

    /**
     *
     * @param reference
     * @param key
     * @return
     */
    public static String getString(final ServiceReference reference,final String key) {
        return (String)reference.getProperty(key);
    }

    /**
     *
     * @param reference
     * @param key
     * @return
     */
    public static Integer getInteger(final ServiceReference reference,final String key) {
        return (Integer)reference.getProperty(key);
    }

    /**
     *
     * @param configAdmin
     * @param pid
     * @return
     */
    public static Dictionary<String,Object> getProperties(ConfigurationAdmin configAdmin,String pid) {
        Dictionary<String, Object> properties = new Hashtable<String, Object>();
        try {
            Configuration config = configAdmin.getConfiguration(pid);
            properties = config.getProperties();
        }
        catch (IOException e) {
            LOGGER.warn("Exception",e);
        }

        return properties;
    }
}
