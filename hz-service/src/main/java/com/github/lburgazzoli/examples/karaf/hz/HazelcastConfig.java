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
package com.github.lburgazzoli.examples.karaf.hz;

import com.hazelcast.config.FileSystemXmlConfig;

import java.io.FileNotFoundException;

/**
 *
 */
public class HazelcastConfig extends FileSystemXmlConfig {

    /**
     *
     * @param path
     * @param classLoader
     */
    public HazelcastConfig(String path, ClassLoader classLoader) throws FileNotFoundException {
        super(path);
        super.setClassLoader(classLoader);
    }

    /*

    public void register(ITypeSerializer<?> typeSerializer) {
    }

    public void unregister(ITypeSerializer<?> typeSerializer) {
    }
      <bean id    = "hz-config"
            class = “com.github.lburgazzoli.osgi.hazelcas.HazelcastConfig”/>

      <reference
        id           = "hz-config"
        interface    = "com.github.lburgazzoli.serialization.ITypeSerializer"
        member-type  = "service-object"
        availability = "optional">

        <reference-listener
            ref           = "hz-config"
            bind-method   = "register"
            unbind-method = "unregister"/>
        </reference>
    */
}
