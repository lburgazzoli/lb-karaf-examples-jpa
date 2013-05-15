
- BUILD


```
gradle clean install

```

- INSTALLATION

```
    features:install jpa
    features:install jndi
    features:install transaction

    install -s mvn:joda-time/joda-time/2.2
    install -s wrap:mvn:com.eaio.uuid/uuid/3.2
    install -s mvn:com.google.guava/guava/14.0.1
    install -s wrap:mvn:org.axonframework/axon-core/2.0
    install -s mvn:com.googlecode.disruptor/disruptor/2.10.3
    install -s mvn:org.apache.commons/commons-lang3/3.1
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.dom4j/1.6.1_5
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.xmlpull/1.1.3.1_2
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.xstream/1.4.4_2
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.aopalliance/1.0_6
    install -s mvn:org.springframework/org.springframework.core/3.2.2.RELEASE
    install -s mvn:org.springframework/org.springframework.expression/3.2.2.RELEASE
    install -s mvn:org.springframework/org.springframework.beans/3.2.2.RELEASE
    install -s mvn:org.springframework/org.springframework.aop/3.2.2.RELEASE
    install -s mvn:org.springframework/org.springframework.context/3.2.2.RELEASE
    install -s mvn:org.springframework/org.springframework.context.support/3.2.2.RELEASE
    install -s mvn:org.springframework/org.springframework.transaction/3.2.2.RELEASE
    install -s mvn:org.apache.openjpa/openjpa/2.2.2
    install -s mvn:org.hibernate.common/hibernate-commons-annotations/4.0.2.Final
    install -s mvn:org.jboss.logging/jboss-logging/3.1.3.GA
    install -s mvn:com.github.lburgazzoli.examples.karaf/osgi-commons/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli.examples.karaf/jpa-utils/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli.examples.karaf/axon-data/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli.examples.karaf/axon-engine/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli.examples.karaf/axon-event-listener/1.0.0.SNAPSHOT

    install -s mvn:http://snapshots.jboss.org/maven2\!org.hibernate/hibernate-core/4.2.2-SNAPSHOT
    install -s mvn:http://snapshots.jboss.org/maven2\!org.hibernate/hibernate-entitymanager/4.2.2-SNAPSHOT
    install -s mvn:http://snapshots.jboss.org/maven2\!org.hibernate/hibernate-osgi/4.2.2-SNAPSHOT
```
