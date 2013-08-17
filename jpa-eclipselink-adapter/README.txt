
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
    install -s mvn:org.hsqldb/hsqldb/2.3.0
    install -s mvn:org.apache.commons/commons-lang3/3.1
    install -s mvn:com.google.guava/guava/14.0.1
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.asm/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.antlr/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.core/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.jpa.jpql/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.jpa/2.4.2

Install the example bundle:

    install -s mvn:com.github.lburgazzoli/karaf-examples-eclipselink-adapter/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

