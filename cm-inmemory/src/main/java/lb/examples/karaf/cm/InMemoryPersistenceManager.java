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

import org.apache.felix.cm.PersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;

/**
 *
 */
public class InMemoryPersistenceManager implements PersistenceManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryPersistenceManager.class);

    @Override
    public boolean exists(String pid) {
        LOGGER.debug("exists : {}",pid);
        return false;
    }

    @Override
    public Dictionary load(String pid) throws IOException {
        LOGGER.debug("load : {}",pid);
        return null;
    }

    @Override
    public Enumeration getDictionaries() throws IOException {
        LOGGER.debug("getDictionaries");
        return null;
    }

    @Override
    public void store(String pid, Dictionary properties) throws IOException {
        LOGGER.debug("store : {}",pid);
    }

    @Override
    public void delete(String pid) throws IOException {
        LOGGER.debug("delete : {}",pid);
    }
}
