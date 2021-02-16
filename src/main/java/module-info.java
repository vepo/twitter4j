module io.vepo.twitter4j {
    requires java.base;
    requires java.net.http;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires org.slf4j;

    exports io.vepo.twitter4j;
    exports io.vepo.twitter4j.stream;
}