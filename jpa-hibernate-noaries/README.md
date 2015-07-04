- BUILD

./gradlew clean install


- INSTALLATION

```
    feature:repo-add https://raw.githubusercontent.com/lburgazzoli/lb-karaf/master/karaf-fetures/lb-karaf-common-deps.xml
    feature:repo-add https://raw.githubusercontent.com/lburgazzoli/lb-karaf/master/karaf-features/lb-karaf-jpa-hibernate-4.xml

    feature:install lb-karaf-common-deps/4.0.0.SNAPSHOT
    feature:install lb-karaf-hibernate-43/4.3.8

    install -s mvn:com.github.lburgazzoli/lb-karaf-common/4.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/lb-karaf-examples-jpa-common/4.0.4.SNAPSHOT
    install -s mvn:com.github.lburgazzoli/lb-karaf-examples-jpa-hibernate-noaries/4.0.4.SNAPSHOT
```

- TEST

```
    item:hibernate-noa-am-add name1 descr1
    item:hibernate-noa-am-add name2 descr2
    item:hibernate-noa-am-list
```
