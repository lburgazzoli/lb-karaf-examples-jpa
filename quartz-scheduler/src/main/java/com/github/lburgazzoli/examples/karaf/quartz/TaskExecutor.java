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
package com.github.lburgazzoli.examples.karaf.quartz;

import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * URI : [scheme:][//authority][path][?query][#fragment]
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class TaskExecutor implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskExecutor.class);

    /**
     * c-tor
     */
    public TaskExecutor() {
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Bundle         bundle  = FrameworkUtil.getBundle(TaskExecutor.class);
        BundleContext  bctx    = bundle.getBundleContext();
        JobDataMap     map     = context.getJobDetail().getJobDataMap();
        TaskDefinition taskdef = (TaskDefinition)map.get(TaskConstants.TASK_DATA_TASK_DEF);
        String         uriStr  = taskdef.get(TaskConstants.TASK_DEF_URI);

        LOGGER.debug("Bundle        : {}",bundle);
        LOGGER.debug("BundleContext : {}",bctx);
        LOGGER.debug("URI           : {}",uriStr);

        for(String key : map.getKeys()) {
            LOGGER.debug("JobDataMap     : {} => {}",key,map.get(key));
        }

        if(StringUtils.isNotBlank(uriStr)) {
            try {
                URI uri  = new URI(uriStr);

                if(uri != null) {
                    LOGGER.debug("Scheme    = {}",uri.getScheme());
                    LOGGER.debug("Authority = {}",uri.getAuthority());
                    LOGGER.debug("Path      = {}",uri.getPath());
                    LOGGER.debug("Query     = {}",uri.getQuery());
                    LOGGER.debug("Fragment  = {}",uri.getFragment());
                }
            } catch(URISyntaxException e) {
                LOGGER.warn("URISyntaxException",e);
            }
        }
    }
}
