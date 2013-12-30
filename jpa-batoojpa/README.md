- BUILD

```
    gradle clean install
```

- INSTALLATION

  
```
    features:install transaction
    features:install jndi
    features:install jpa

    install -s mvn:com.google.guava/guava/15.0
    install -s mvn:commons-dbutils/commons-dbutils/1.5
    install -s mvn:commons-io/commons-io/2.4
    install -s mvn:commons-lang/commons-lang/2.6
    install -s mvn:org.apache.commons/commons-lang3/3.1
    install -s mvn:org.apache.servicemix.specs/org.apache.servicemix.specs.jsr303-api-1.0.0/2.2.0
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.antlr-runtime/3.4_2
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.asm/3.3_2
    install -s wrap:mvn:org.jboss/jandex/1.1.0.Final
    install -s wrap:mvn:org.jboss.as/jboss-as-jpa-spi/7.2.0.Final
    install -s mvn:org.hsqldb/hsqldb/2.3.0
    install -s mvn:org.batoo.jpa/batoo-jpa/2.0.1.3-SNAPSHOT

    install -s mvn:com.github.lburgazzoli/lb-karaf-common/3.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-common/3.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-batoojpa/3.0.0.SNAPSHOT

    stop  ${aries-jpa-bundle}
    start ${aries-jpa-bundle}
```


- TEST

```
    item:batoo-am-add name1 descr1
    item:batoo-am-add name2 descr2
    item:batoo-am-list
```
