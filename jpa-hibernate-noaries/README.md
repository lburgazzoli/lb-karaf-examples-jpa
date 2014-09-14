- BUILD

./gradlew clean install


- INSTALLATION

```
    feature:repo-add https://raw.github.com/lburgazzoli/lb-karaf-features/master/repo/lb-karaf-common-deps.xml
    feature:repo-add https://raw.github.com/lburgazzoli/lb-karaf-features/master/repo/lb-karaf-hibernate-4.xml

    feature:install lb-karaf-common-deps/3.0.1.SNAPSHOT
    feature:install lb-karaf-hibernate-43/4.3.6

    install -s mvn:com.github.lburgazzoli/karaf-common/3.0.1.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-common/3.0.1.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/karaf-examples-jpa-hibernate-noaries/3.0.1.SNAPSHOT
```

- TEST

```
    item:hibernate-noa-am-add name1 descr1
    item:hibernate-noa-am-add name2 descr2
    item:hibernate-noa-am-list
```
