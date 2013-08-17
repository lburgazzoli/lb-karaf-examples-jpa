
BUILD
--------------------------------------------------------------------------------

gradle clean install


INSTALLATION
--------------------------------------------------------------------------------

Install Karaf's enterprise features:

    features:install transaction
    features:install jndi
    features:install jpa


Install bundles:
    install -s mvn:org.hsqldb/hsqldb/2.2.9
    install -s mvn:org.apache.commons/commons-lang3/3.1
    install -s mvn:com.google.guava/guava/14.0.1

    # eclipselink 2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.asm/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.antlr/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.jpa.jpql/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.jpa/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.core/2.4.2

    # eclipselink 2.5.0 - not yet working
    #install -s mvn:org.apache.aries.jpa/org.apache.aries.jpa.api/1.0.1-SNAPSHOT
    #install -s mvn:org.apache.aries.jpa/org.apache.aries.jpa.blueprint.aries/1.0.2-SNAPSHOT
    #install -s mvn:org.apache.aries.jpa/org.apache.aries.jpa.container/1.0.1-SNAPSHOT
    #install -s mvn:org.apache.aries.jpa/org.apache.aries.jpa.container.context/1.0.2-SNAPSHOT
           
    #install -s mvn:org.eclipse.persistence/org.eclipse.persistence.asm/2.5.0
    #install -s mvn:org.eclipse.persistence/org.eclipse.persistence.antlr/2.5.0
    #install -s mvn:org.eclipse.persistence/org.eclipse.persistence.jpa.jpql/2.5.0
    #install -s mvn:org.eclipse.persistence/org.eclipse.persistence.jpa/2.5.0
    #install -s mvn:org.eclipse.persistence/org.eclipse.persistence.core/2.5.0
    #install -s mvn:org.eclipse.persistence/javax.persistence/2.1.0
    #install mvn:org.eclipse.persistence/org.eclipse.persistence.oracle/2.5.0

Install the example bundle:
    cp datasources/jpa-datasources.xml $KARAF_HOME/deploy

    install -s mvn:com.github.lburgazzoli/karaf-examples-osgi-commons/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-utils/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-commons/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-eclipselink-adapter/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-eclipselink/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    item:eclipselink-am-add name1 descr1
    item:eclipselink-am-add name2 descr2
    item:eclipselink-am-list
