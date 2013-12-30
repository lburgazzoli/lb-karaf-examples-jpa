
BUILD
--------------------------------------------------------------------------------

gradle clean install


INSTALLATION
--------------------------------------------------------------------------------

Install Karaf's enterprise features:

    feature:install transaction
    feature:install jndi
    feature:install jpa


Install bundles:

Install the example bundle:

    install -s mvn:com.github.lburgazzoli/karaf-examples-kundera-adapter/3.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

