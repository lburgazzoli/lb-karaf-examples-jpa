
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
    install -s mvn:com.google.guava/guava/13.0
    install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.asm/3.3.1.v201206041142
    install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.antlr/3.2.0.v201206041011
    install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.antlr/3.2.0.v201206041011
    install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.core/2.4.1
    install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.jpa.jpql/2.4.1
    install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.jpa/2.4.1

Install the example bundle:
    cp datasources/jpa-datasources.xml $KARAF_HOME/deploy

    install -s mvn:karaf-examples/jpa-eclipselink-adapter/1.0.0.SNAPSHOT
    install -s mvn:karaf-examples/jpa-eclipselink/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    item:add name1 descr1
    item:add name2 descr2
    item:list
    name1, descr1
    name2, descr2
