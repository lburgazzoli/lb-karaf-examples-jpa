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
package com.github.lburgazzoli.examples.karaf.axon.engine;

import com.lmax.disruptor.SingleThreadedClaimStrategy;
import com.lmax.disruptor.SleepingWaitStrategy;
import org.axonframework.commandhandling.disruptor.DisruptorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class SimpleDisruptorConfiguration extends DisruptorConfiguration {
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDisruptorConfiguration.class);

    /**
     * c-tor
     */
    public SimpleDisruptorConfiguration() {
        super.setExecutor(EXECUTOR);
        super.setClaimStrategy(new SingleThreadedClaimStrategy(8));
        super.setWaitStrategy(new SleepingWaitStrategy());
        super.setInvokerThreadCount(2);
        super.setPublisherThreadCount(3);
    }

    /**
     *
     * @param bufferSize
     */
    public void setSingleThreadedClaimStrategyBuffer(int bufferSize) {
        super.setClaimStrategy(new SingleThreadedClaimStrategy(bufferSize));
    }
}
