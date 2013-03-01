
BUILD
--------------------------------------------------------------------------------

gradle clean install


INSTALLATION
--------------------------------------------------------------------------------

Install Karaf's enterprise features:

    karaf@root> features:install transaction
    karaf@root> features:install jndi
    karaf@root> features:install jpa


Install bundles:
    karaf@root> install -s mvn:org.hsqldb/hsqldb/2.2.9
    karaf@root> install -s mvn:org.apache.commons/commons-lang3/3.1
    karaf@root> install -s mvn:com.google.guava/guava/13.0
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.asm/3.3.1.v201206041142
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!mvn:org.eclipse.persistence/org.eclipse.persistence.antlr/3.2.0.v201206041011
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.antlr/3.2.0.v201206041011
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.core/2.4.1
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.jpa.jpql/2.4.1
    karaf@root> install -s mvn:http://download.eclipse.org/rt/eclipselink/maven.repo\!org.eclipse.persistence/org.eclipse.persistence.jpa/2.4.1

Install the example bundle:

    karaf@root> install -s mvn:karaf-examples/jpa-eclipselink-adapter/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

