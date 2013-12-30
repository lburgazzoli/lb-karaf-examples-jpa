
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

    install -s mvn:com.github.lburgazzoli/lb-karaf-common/3.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-common/3.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-kundera/3.0.0.SNAPSHOT

TEST
--------------------------------------------------------------------------------

