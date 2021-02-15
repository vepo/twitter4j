package io.vepo.twitter4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.jparams.verifier.tostring.ToStringVerifier;

import nl.jqno.equalsverifier.EqualsVerifier;

@DisplayName("Test Model")
class ModelTest {

    @ParameterizedTest
    @ValueSource(classes = {
        Rule.class,
        Tweet.class,
        TweetData.class,
        TweetIncludeData.class,
        TweetMatchingRule.class,
        TweetUserData.class
    })
    @DisplayName("Check equals: {0}")
    void equalsTest(Class<?> modelClass) {
        EqualsVerifier.simple()
                      .forClass(modelClass)
                      .usingGetClass()
                      .verify();
    }

    @ParameterizedTest
    @ValueSource(classes = {
        Rule.class,
        Tweet.class,
        TweetData.class,
        TweetIncludeData.class,
        TweetMatchingRule.class,
        TweetUserData.class
    })
    @DisplayName("Check toString: {0}")
    void toStringTest(Class<?> modelClass) {
        ToStringVerifier.forClass(modelClass)
                        .verify();
    }
}
