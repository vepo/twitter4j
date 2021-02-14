module io.vepo.twitter4j {
    requires java.base;
    requires java.net.http;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    exports io.vepo.twitter4j;
}