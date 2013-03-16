
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

    install -s mvn:karaf-examples/jpa-utils/1.0.0.SNAPSHOT
    install -s mvn:karaf-examples/jpa-commons/1.0.0.SNAPSHOT
    install -s mvn:karaf-examples/jpa-kundera/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

