
BUILD
--------------------------------------------------------------------------------

gradle clean install


INSTALLATION
--------------------------------------------------------------------------------

Install the example bundle:

    karaf@root> install -s mvn:karaf-examples/jpa-utils/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    karaf@root> jpa:datasource-list
    BundleID | BundleName                | JNDI
    ----------------------------------------------
    178      | karaf.examples.datasource | jdbc/DS
    
    karaf@root> jpa:provider-list
    BundleID | BundleName         | PersistenceProvider
    --------------------------------------------------------------------------------------
    138      | org.apache.openjpa | org.apache.openjpa.persistence.PersistenceProviderImpl
    198      | org.hibernate.osgi | org.hibernate.osgi.HibernateBundleActivator
