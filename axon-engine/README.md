
BUILD
--------------------------------------------------------------------------------

gradle clean install


INSTALLATION
--------------------------------------------------------------------------------

Install bundles:
    install -s wrap:mvn:com.eaio.uuid/uuid/3.2
    install -s wrap:mvn:org.axonframework/axon-core/2.0
    install -s mvn:com.googlecode.disruptor/disruptor/2.10.3
    install -s mvn:joda-time/joda-time/2.2
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.xmlpull/1.1.3.1_2
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.xstream/1.4.4_2

Install the example bundle:
    install -s mvn:com.github.lburgazzoli.examples.karaf/osgi-commons/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli.examples.karaf/axon-data/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli.examples.karaf/axon-engine/1.0.0.SNAPSHOT
    install -s mvn:com.github.lburgazzoli.examples.karaf/axon-event-listener/1.0.0.SNAPSHOT
