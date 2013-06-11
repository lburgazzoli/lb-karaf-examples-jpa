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
package com.github.lburgazzoli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 *
 */
public class Utils {
    /**
     *
     * @param classLoader
     * @param runnable
     * @return
     */
    public static final Thread newThread(ClassLoader classLoader,Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setContextClassLoader(classLoader);

        return thread;
    }

    /**
     *
     * @param collection
     * @param type
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> downCastCollection(
        Collection<? extends T> collection,Class<T> type) {
        return (Collection<T>) collection;
    }

    /**
     *
     * @param classLoader
     * @return
     */
    public static ClassLoader swapContextClassLoader(ClassLoader classLoader) {
        ClassLoader oldClassLoader =  Thread.currentThread().getContextClassLoader();

        if(classLoader != null) {
            Thread.currentThread().setContextClassLoader(classLoader);
        }

        return oldClassLoader;
    }

    /**
     *
     * @param fmt
     * @param args
     * @return
     */
    public static Logger getLogger(String fmt,Object... args) {
        return LoggerFactory.getLogger(String.format(fmt, args));
    }

    /**
     *
     * @param type
     * @return
     */
    public static <T> Logger getLogger(Class<T> type) {
        return LoggerFactory.getLogger(type);
    }
}
