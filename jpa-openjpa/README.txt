
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
    karaf@root> install -s mvn:org.apache.geronimo.specs/geronimo-servlet_2.5_spec/1.2
    karaf@root> install -s mvn:org.hsqldb/hsqldb/2.2.9
    karaf@root> install -s mvn:org.apache.commons/commons-lang3/3.1
    karaf@root> install -s mvn:commons-lang/commons-lang/2.6
    karaf@root> install -s mvn:commons-pool/commons-pool/1.5.4
    karaf@root> install -s mvn:commons-collections/commons-collections/3.2.1
    karaf@root> install -s mvn:com.google.guava/guava/13.0
    karaf@root> install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.commons-dbcp/1.4_3
    karaf@root> install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.serp/1.14.1_1
    karaf@root> install -s mvn:org.apache.openjpa/openjpa/2.2.1

Install the example bundle:
    cp datasources/jpa-datasources.xml $KARAF_HOME/deploy

    karaf@root> install -s mvn:karaf-examples/jpa-openjpa/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    karaf@root> item:add name1 descr1
    karaf@root> item:add name2 descr2
    karaf@root> item:list
    name1, descr1
    name2, descr2
