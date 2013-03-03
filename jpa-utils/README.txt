
BUILD
--------------------------------------------------------------------------------

gradle clean install


INSTALLATION
--------------------------------------------------------------------------------

Install the example bundle:

    karaf@root> install -s mvn:karaf-examples/jpa-utils/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    karaf@root> jpa:provider-list
