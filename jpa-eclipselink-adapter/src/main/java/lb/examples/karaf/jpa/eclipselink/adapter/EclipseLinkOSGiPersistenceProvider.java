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
package lb.examples.karaf.jpa.eclipselink.adapter;

import org.eclipse.persistence.jpa.PersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import java.util.Map;

/**
 *
 */
public class EclipseLinkOSGiPersistenceProvider extends PersistenceProvider {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(EclipseLinkOSGiPersistenceProvider.class);

    /**
     * c-tor
     */
    public EclipseLinkOSGiPersistenceProvider() {
    }

    @Override
    public EntityManagerFactory createEntityManagerFactory(String emName, Map map) {
        LOGGER.debug(">>> createEntityManagerFactory <{}|{}>",emName,map);
        return super.createEntityManagerFactory(emName, map);
    }

    @Override
    public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map map) {
        LOGGER.debug(">>> createContainerEntityManagerFactory <{}|{}>",info,map);
        return super.createContainerEntityManagerFactory(info,map);
    }
}
