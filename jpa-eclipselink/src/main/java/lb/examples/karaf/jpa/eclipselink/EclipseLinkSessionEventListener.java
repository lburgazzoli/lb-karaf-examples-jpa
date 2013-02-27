package lb.examples.karaf.jpa.eclipselink;

import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;

/**
 *
 */
public class EclipseLinkSessionEventListener extends SessionEventAdapter {
    @Override
    public void preLogin(SessionEvent event) {
     super.preLogin(event);
    }
}
