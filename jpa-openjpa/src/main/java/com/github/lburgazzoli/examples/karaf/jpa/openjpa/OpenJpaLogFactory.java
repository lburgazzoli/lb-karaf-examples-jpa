/**
 *
 * Copyright 2013 Luca Burgazzoli (lburgazzoli)
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
package com.github.lburgazzoli.examples.karaf.jpa.openjpa;

import org.apache.openjpa.lib.log.Log;
import org.apache.openjpa.lib.log.LogFactoryAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class OpenJpaLogFactory extends LogFactoryAdapter {

    protected Log newLogAdapter(String channel) {
        return new LogAdapter(LoggerFactory.getLogger(channel));
    }

    /**
     *
     */
    public static class LogAdapter implements Log {

        private Logger m_logger;

        private LogAdapter(Logger wrapee) {
            m_logger = wrapee;
        }

        public Logger getDelegate() {
            return m_logger;
        }

        @Override
        public boolean isTraceEnabled() {
            return m_logger.isTraceEnabled();
        }

        public boolean isDebugEnabled() {
            return m_logger.isDebugEnabled();
        }

        @Override
        public boolean isInfoEnabled() {
            return m_logger.isInfoEnabled();
        }

        @Override
        public boolean isWarnEnabled() {
            return m_logger.isWarnEnabled();
        }

        @Override
        public boolean isErrorEnabled() {
            return m_logger.isErrorEnabled();
        }

        @Override
        public boolean isFatalEnabled() {
            return m_logger.isErrorEnabled();
        }

        @Override
        public void trace(Object o) {
            m_logger.trace(objectToString(o));
        }

        @Override
        public void trace(Object o, Throwable t) {
            m_logger.trace(objectToString(o), t);
        }

        public void debug(Object o) {
            m_logger.debug(objectToString(o));
        }

        public void debug(Object o, Throwable t) {
            m_logger.debug(objectToString(o), t);
        }

        @Override
        public void info(Object o) {
            m_logger.info(objectToString(o));
        }

        @Override
        public void info(Object o, Throwable t) {
            m_logger.info(objectToString(o), t);
        }

        @Override
        public void warn(Object o) {
            m_logger.warn(objectToString(o));
        }

        @Override
        public void warn(Object o, Throwable t) {
            m_logger.warn(objectToString(o), t);
        }

        @Override
        public void error(Object o) {
            m_logger.error(objectToString(o));
        }

        @Override
        public void error(Object o, Throwable t) {
            m_logger.error(objectToString(o), t);
        }

        @Override
        public void fatal(Object o) {
            m_logger.error(objectToString(o));
        }

        @Override
        public void fatal(Object o, Throwable t) {
            m_logger.error(objectToString(o), t);
        }

        private String objectToString(Object o) {
            return (o == null) ? (String)o : o.toString();
        }
    }
}

