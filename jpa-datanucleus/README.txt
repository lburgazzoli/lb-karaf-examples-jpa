
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

Install the example bundle:
    install -s mvn:javax.jdo/jdo-api/3.0.1
    install -s mvn:org.datanucleus/datanucleus-core/3.1.4
    install -s mvn:org.datanucleus/datanucleus-enhancer/3.1.1
    install -s mvn:org.datanucleus/datanucleus-cache/3.1.1
    install -s mvn:org.datanucleus/datanucleus-rdbms/3.1.4
    install -s mvn:org.datanucleus/datanucleus-api-jdo/3.1.3
    install -s mvn:org.datanucleus/datanucleus-api-jpa/3.1.4

    install -s mvn:karaf-examples/jpa-datanucleus/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    item:hibernate-am-add name1 descr1
    item:hibernate-am-add name2 descr2
    item:hibernate-am-list
