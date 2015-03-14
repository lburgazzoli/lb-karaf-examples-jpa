- BUILD

./gradlew clean install


- INSTALLATION

```
    features:install transaction
    features:install jndi
    features:install jpa

    feature:repo-add https://raw.github.com/lburgazzoli/lb-karaf/master/karaf-features/lb-karaf-common-deps.xml
    feature:repo-add https://raw.github.com/lburgazzoli/lb-karaf/master/karaf-features/lb-karaf-jpa-hibernate-4.xml

    feature:install lb-karaf-common-deps/3.0.3.SNAPSHOT
    feature:install lb-karaf-hibernate-42/4.2.7

    install -s mvn:com.github.lburgazzoli/lb-karaf-common/3.0.3.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-common/3.0.3.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-hibernate/3.0.3.SNAPSHOT
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
