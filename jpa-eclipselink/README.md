
- IMPORTANT INFO

You need the separate what is in the jpa-eclipselink-adapter in its own bundle. This is to avoid a race condition with EclipseLink and the way it starts the persistence provider.

- BUILD


```
gradle clean install

```

- INSTALLATION

```
    features:install transaction
    features:install jndi
    features:install jpa

    install -s mvn:org.hsqldb/hsqldb/2.3.0
    install -s mvn:org.apache.commons/commons-lang3/3.1
    install -s mvn:com.google.guava/guava/15.0
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.asm/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.antlr/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.jpa.jpql/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.core/2.4.2
    install -s mvn:org.eclipse.persistence/org.eclipse.persistence.jpa/2.4.2

    install -s mvn:com.github.lburgazzoli/lb-karaf-common/3.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-common/3.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-eclipselink-adapter/3.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-eclipselink/3.0.0.SNAPSHOT
```

- INSTALLATION

```
    item:eclipselink-am-add name1 descr1
    item:eclipselink-am-add name2 descr2
    item:eclipselink-am-list
    item:eclipselink-cm-add name3 descr3
    item:eclipselink-cm-add name4 descr3
    item:eclipselink-cm-list
```
