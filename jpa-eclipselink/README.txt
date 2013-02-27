

Installation
-----------------------------------

First install Karaf's enterprise features:

    karaf@root> features:install transaction
    karaf@root> features:install jndi
    karaf@root> features:install jpa


Then install some useful bundles:

    karaf@root> install mvn:org.apache.commons/commons-lang3/3.1


Then install EclipseLink bundles:

    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.asm/3.3.1.v201206041142
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!mvn:org.eclipse.persistence/org.eclipse.persistence.antlr/3.2.0.v201206041011
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.antlr/3.2.0.v201206041011
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.core/2.4.1
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.jpa.jpql/2.4.1
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.jpa/2.4.1


