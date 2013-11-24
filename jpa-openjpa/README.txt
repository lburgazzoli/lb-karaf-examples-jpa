
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
    install -s mvn:commons-lang/commons-lang/2.6
    install -s mvn:commons-pool/commons-pool/1.6
    install -s mvn:commons-collections/commons-collections/3.2.1
    install -s mvn:com.google.guava/guava/15.0
    install -s mvn:org.apache.geronimo.specs/geronimo-servlet_2.5_spec/1.2
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.commons-dbcp/1.4_3
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.serp/1.14.1_1
    install -s mvn:com.esotericsoftware.kryo/kryo/2.21
    install -s mvn:com.fasterxml.jackson.core/jackson-core/2.2.2
    install -s mvn:com.fasterxml.jackson.core/jackson-annotations/2.2.2
    install -s mvn:com.fasterxml.jackson.core/jackson-databind/2.2.2
    install -s mvn:de.undercouch/bson4jackson/2.2.0
    install -s mvn:org.apache.openjpa/openjpa/2.3.0-SNAPSHOT

    install -s mvn:com.github.lburgazzoli/lb-karaf-common/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-commons-jpa/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-openjpa/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    item:openjpa-am-add name1 descr1
    item:openjpa-am-add name2 descr2
    item:openjpa-am-list

    item:openjpa-cm-add name3 descr4
    item:openjpa-cm-add name4 descr5
    item:openjpa-cm-list
