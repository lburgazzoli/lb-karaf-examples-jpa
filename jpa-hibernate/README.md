 BUILD

gradle clean install


- INSTALLATION

  - Install Karaf's enterprise features:

```
    features:install transaction
    features:install jndi
    features:install jpa
```



  - Install bundles:

```
    install -s mvn:org.hsqldb/hsqldb/2.3.0
    install -s mvn:com.fasterxml/classmate/0.9.0
    install -s mvn:org.apache.geronimo.specs/geronimo-servlet_3.0_spec/1.0
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.antlr/2.7.7_5
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.ant/1.8.2_2
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.dom4j/1.6.1_5
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.serp/1.14.1_1
    install -s mvn:org.javassist/javassist/3.18.1-GA
    install -s mvn:org.jboss.spec.javax.security.jacc/jboss-jacc-api_1.4_spec/1.0.2.Final
    install -s wrap:mvn:org.jboss/jandex/1.1.0.Final
    install -s mvn:org.jboss.logging/jboss-logging/3.1.3.GA

    install -s mvn:org.hibernate.common/hibernate-commons-annotations/4.0.4.Final
    install -s mvn:org.hibernate/hibernate-core/4.2.2.Final
    install -s mvn:org.hibernate/hibernate-entitymanager/4.2.2.Final
    install -s mvn:org.hibernate/hibernate-envers/4.2.2.Final
    install mvn:org.hibernate/hibernate-osgi/4.2.2.Final
```

  - Install the example bundle:

```
    cp datasources/jpa-datasources.xml $KARAF_HOME/deploy

    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-utils/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-commons/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-hibernate/1.0.0.SNAPSHOT

    start ${hibernate-osgi}
```

- TEST

```
    item:hibernate-am-add name1 descr1
    item:hibernate-am-add name2 descr2
    item:hibernate-cm-add name3 descr3
    item:hibernate-cm-add name4 descr4
    item:hibernate-am-list
    item:hibernate-cm-list
```
