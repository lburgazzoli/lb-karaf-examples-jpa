
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

    install -s mvn:com.fasterxml/classmate/0.5.4
    install -s mvn:javax.servlet/javax.servlet-api/3.0.1
    install -s mvn:org.hibernate/hibernate-validator/4.2.0.Final
    install -s mvn:org.apache.geronimo.specs/geronimo-validation_1.0_spec/1.1
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.antlr/2.7.7_5
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.ant/1.8.2_2
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.dom4j/1.6.1_5
    install -s mvn:org.jboss.javassist/com.springsource.javassist/3.15.0.GA
    install -s mvn:org.jboss.spec.javax.security.jacc/jboss-jacc-api_1.4_spec/1.0.2.Final
    install -s wrap:mvn:org.jboss/jandex/1.1.0.Alpha1
    install -s mvn:org.jboss.logging/jboss-logging/3.1.0.GA

    install -s wrap:mvn:https://repository.jboss.org/nexus/content/repositories/snapshots\!org.hibernate/common/hibernate-commons-annotations/4.0.1-SNAPSHOT
    install -s mvn:https://repository.jboss.org/nexus/content/repositories/snapshots\!org.hibernate/hibernate-core/4.3.0-SNAPSHOT
    install -s mvn:https://repository.jboss.org/nexus/content/repositories/snapshots\!org.hibernate/hibernate-entitymanager/4.3.0-SNAPSHOT
    install -s mvn:https://repository.jboss.org/nexus/content/repositories/snapshots\!org.hibernate/hibernate-osgi/4.3.0-SNAPSHOT

Install the example bundle:
    cp hotdeploy/jpa-datasource-hsql.xml $KARAF_HOME/deploy

    karaf@root> install -s mvn:karaf-examples/jpa-hibenrate/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    karaf@root> item:add name1 descr1
    karaf@root> item:add name2 descr2
    karaf@root> item:list
    name1, descr1
    name2, descr2
