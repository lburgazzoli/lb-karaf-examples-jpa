
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
    install -s mvn:com.google.guava/guava/14.0.1
    install -s wrap:mvn:javax.validation/validation-api/1.0.0.GA
    install -s mvn:commons-dbutils/commons-dbutils/1.5
    install -s mvn:commons-io/commons-io/2.4
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.antlr-runtime/3.4_2
    install -s wrap:mvn:org.jboss/jandex/1.1.0.Alpha1
    install -s wrap:mvn:org.jboss.as/jboss-as-jpa-spi/7.1.1.Final


Install the example bundle:
    cp datasources/jpa-datasources.xml $KARAF_HOME/deploy

    install -s mvn:karaf-examples/jpa-utils/1.0.0.SNAPSHOT
    install -s mvn:karaf-examples/jpa-commons/1.0.0.SNAPSHOT
    install -s mvn:karaf-examples/jpa-batoojap/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    item:batoo-am-add name1 descr1
    item:batoo-am-add name2 descr2
    item:batoo-am-list
