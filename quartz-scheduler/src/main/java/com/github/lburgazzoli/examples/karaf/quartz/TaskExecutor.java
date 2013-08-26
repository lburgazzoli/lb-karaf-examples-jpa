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
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        Bundle         bundle   = FrameworkUtil.getBundle(TaskExecutor.class);
        BundleContext  bctx     = bundle.getBundleContext();
        JobDataMap     map      = context.getJobDetail().getJobDataMap();
        TaskDefinition taskDef  = (TaskDefinition)map.get(TaskConstants.TASK_DATA_TASK_DEF);
        String         taskType = taskDef.get(TaskConstants.TASK_DEF_TYPE);

        if(StringUtils.equals(TaskConstants.TASK_DEF_TYPE_OSGI,taskType)) {
            String type   = taskDef.get(TaskConstants.TASK_DEF_OSGI_TYPE);
            String method = taskDef.get(TaskConstants.TASK_DEF_OSGI_METHOD);
            String filter = taskDef.get(TaskConstants.TASK_DEF_OSGI_FILTER);

            LOGGER.debug("OSGi Type   <{}>",type);
            LOGGER.debug("OSGi Method <{}>",method);
            LOGGER.debug("OSGi Filter <{}>",filter);

            if(StringUtils.isNotBlank(type) && StringUtils.isNotBlank(filter)) {
                try {
                    ServiceReference<?>[] refs =
                        bctx.getServiceReferences(type, filter);

                    if(refs != null) {
                        for(ServiceReference<?> sr : refs) {
                            try {
                                Object service = bctx.getService(sr);
                                Method action  = service.getClass().getMethod(method);

                                if(!action.isAccessible()) {
                                    action.setAccessible(true);
                                }

                                action.invoke(service);
                            } catch (NoSuchMethodException e) {
                                LOGGER.warn("NoSuchMethodException", e);
                            } catch (InvocationTargetException e) {
                                LOGGER.warn("InvocationTargetException", e);
                            } catch (IllegalAccessException e) {
                                LOGGER.warn("IllegalAccessException", e);
                            } finally {
                                bctx.ungetService(sr);
                            }
                        }
                    } else {
                        LOGGER.debug("No service found <{}/{}>",type,filter);
                    }
                } catch (InvalidSyntaxException e) {
                    LOGGER.warn("InvalidSyntaxException",e);
                }
            }
        }
    }
}
