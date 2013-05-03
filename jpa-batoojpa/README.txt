
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
    cp datasources/jpa-datasources.xml $KARAF_HOME/deploy

    install -s mvn:karaf-examples/jpa-utils/1.0.0.SNAPSHOT
    install -s mvn:karaf-examples/jpa-commons/1.0.0.SNAPSHOT
    install -s mvn:karaf-examples/jpa-batoojpa-adapter/1.0.0.SNAPSHOT
    install -s mvn:karaf-examples/jpa-batoojap/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    item:batoo-am-add name1 descr1
    item:batoo-am-add name2 descr2
    item:batoo-am-list
