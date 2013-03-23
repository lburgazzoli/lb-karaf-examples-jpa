
BUILD
--------------------------------------------------------------------------------

gradle clean install


INSTALLATION
--------------------------------------------------------------------------------

Install bundles:
    install the latest 3.4.* development snapshot

Install the example bundle:
    cat << EOF
        initLimit  = 10
        tickTime   = 2000
        clientPort = 2181
        dataDir    = ${karad.data}/zookeeper
        syncLimit  = 5
    > $KARAF_HOME/etc/zk.service.cfg

    install -s mvn:karaf-examples/zookeeper-server/1.0.0.SNAPSHOT
