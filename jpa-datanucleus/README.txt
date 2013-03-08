
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

    install -s mvn:karaf-examples/jpa-datanucleus/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    item:hibernate-am-add name1 descr1
    item:hibernate-am-add name2 descr2
    item:hibernate-am-list
