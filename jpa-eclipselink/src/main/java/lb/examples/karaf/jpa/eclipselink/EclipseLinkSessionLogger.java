package lb.examples.karaf.jpa.eclipselink;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 *
 */
public class EclipseLinkSessionLogger extends AbstractSessionLog implements SessionLog
{
    private static final Logger LOGGER  = LoggerFactory.getLogger("ECLIPSELINK");
    private static final Map<String,Logger> LOGGERS = Maps.newHashMap();

    /**
     * c-tor
     */
    public EclipseLinkSessionLogger() {
        this(1);
    }

    /**
     * c-tor
     *
     * @param level
     */
    public EclipseLinkSessionLogger(int level) {
        setLevel(1);
    }

    /**
     *
     */
    @Override
    public void log(SessionLogEntry sle) {
        Logger lg  = getLogger(sle);
        String msg = getMessage(sle);

        if(lg != null && StringUtils.isNotBlank(msg)) {
            switch(sle.getLevel()) {
                case SessionLog.SEVERE  : lg.error(msg); break;
                case SessionLog.WARNING : lg.warn (msg); break;
                case SessionLog.INFO    : lg.info (msg); break;
                case SessionLog.FINE    : lg.debug(msg); break;
                case SessionLog.FINER   : lg.trace(msg); break;
                case SessionLog.FINEST  : lg.trace(msg); break;
                case SessionLog.ALL     : lg.trace(msg); break;
                default                 : lg.debug(msg); break;
            }
        }
    }

    /**
     *
     * @param sle
     * @return
     */
    private String getMessage(SessionLogEntry sle) {
        StringBuilder msg = new StringBuilder();
        //msg.append(getSupplementDetailString(sle))
        msg.append(formatMessage(sle));

        return msg.toString();
    }

    /**
     *
     * @param sle
     * @return
     */
    private Logger getLogger(SessionLogEntry sle) {
        String ns = sle.getNameSpace();
        if(StringUtils.isNotBlank(ns)) {
            ns = ns.toUpperCase();

            if(!LOGGERS.containsKey(ns)) {
                LOGGERS.put(ns,LoggerFactory.getLogger("JPA-" + ns));
            }

            return LOGGERS.get(ns);
        }

        return LOGGER;
    }
}
