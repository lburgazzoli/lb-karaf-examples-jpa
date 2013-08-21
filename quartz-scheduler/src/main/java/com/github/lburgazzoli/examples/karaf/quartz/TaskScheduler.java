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
import org.osgi.framework.BundleContext;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class TaskScheduler implements ITaskScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskScheduler.class);

    private SchedulerFactory m_schedulerFactory;
    private Scheduler m_scheduler;
    private BundleContext m_bundleContext;
    private int m_schedulerStartDelay;

    /**
     * c-tor
     */
    public TaskScheduler() {
        m_schedulerFactory = null;
        m_scheduler = null;
        m_schedulerStartDelay = 0;
        m_bundleContext = null;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param schedulerFactory
     */
    public void setSchedulerFactory(SchedulerFactory schedulerFactory) {
        m_schedulerFactory = schedulerFactory;
    }

    /**
     *
     * @param schedulerStartDelay
     */
    public void setSchedulerStartDelay(int schedulerStartDelay) {
        m_schedulerStartDelay = schedulerStartDelay;
    }

    /**
     *
     * @param bundleContex
     */
    public void setBundleContext(BundleContext bundleContex) {
        m_bundleContext = bundleContex;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     */
    public void init() {
        if(m_schedulerFactory != null) {
            try {
                m_scheduler = m_schedulerFactory.getScheduler();
                if(m_schedulerStartDelay > 0) {
                    m_scheduler.startDelayed(m_schedulerStartDelay);
                } else {
                    m_scheduler.start();
                }
            } catch(SchedulerException e) {
                LOGGER.warn("SchedulerException",e);
            }
        }
    }

    /**
     *
     */
    public void destroy() {
        if(m_scheduler != null) {
            try {
                m_scheduler.shutdown();
            } catch(SchedulerException e) {
                LOGGER.warn("SchedulerException",e);
            }
        }

        m_scheduler = null;
        m_schedulerFactory = null;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @return
     */
    public Scheduler getScheduler()
    {
        return m_scheduler;
    }

    @Override
    public void schedule(TaskDefinition definition) throws Exception{
        String scheme = definition.get(TaskConstants.TASK_DEF_URI  );
        String cron   = definition.get(TaskConstants.TASK_DEF_CRON );
        String tid    = definition.get(TaskConstants.TASK_DEF_ID   );
        String tgrp   = definition.get(TaskConstants.TASK_DEF_GROUP);

        if( StringUtils.isNotBlank(scheme) &&
            StringUtils.isNotBlank(cron  ) &&
            StringUtils.isNotBlank(tid   ) &&
            StringUtils.isNotBlank(tgrp  ) ) {

            JobDetail job = JobBuilder.newJob(Task.class)
                .withIdentity("j-" + tid, tgrp)
                .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("t-" + tid, tgrp)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                 .build();

            job.getJobDataMap().put(TaskConstants.TASK_DATA_BUNDLE_CTX,m_bundleContext);
            job.getJobDataMap().put(TaskConstants.TASK_DATA_TASK_DEF  ,definition);

            m_scheduler.scheduleJob(job,trigger);
        }
    }


}
