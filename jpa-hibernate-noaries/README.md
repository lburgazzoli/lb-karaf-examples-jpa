 BUILD

gradle clean install


- INSTALLATION

```
    feature:repo-add file:${SOMEWHERE}/lb-karaf-examples-jpa/jpa-hibernate-noaries/karaf-features.xml

    feature:install trasaction
    feature:install jndi
    feature:install jdbc
    feature:install jpa-hibernate-noaries
```

- TEST

```
    item:hibernate-noa-am-add name1 descr1
    item:hibernate-noa-am-add name2 descr2
    item:hibernate-noa-am-list
```
