
BUILD
--------------------------------------------------------------------------------

gradle clean install


INSTALLATION
--------------------------------------------------------------------------------

Install bundles:
    install wrap:mvn:com.netflix.curator/curator-client/1.3.3
    install wrap:mvn:com.netflix.curator/curator-framework/1.3.3

Install the example bundle:
    install -s mvn:karaf-examples/zookeeper-client/1.0.0.SNAPSHOT

