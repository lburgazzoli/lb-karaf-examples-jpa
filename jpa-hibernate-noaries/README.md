- BUILD

./gradlew clean install


- INSTALLATION

```
    feature:repo-add https://raw.github.com/lburgazzoli/lb-karaf-features/master/repo/lb-karaf-common.xml
    feature:repo-add https://raw.github.com/lburgazzoli/lb-karaf-features/master/repo/hibernate4.xml

    feature:install hibernate43/4.3.0
    feature:install lb-karaf-common/3.0.0.SNAPSHOT

    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-common/3.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-hibernate-noaries/3.0.0.SNAPSHOT
```

- TEST

```
    item:hibernate-noa-am-add name1 descr1
    item:hibernate-noa-am-add name2 descr2
    item:hibernate-noa-am-list
```
