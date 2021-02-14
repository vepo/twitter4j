package io.vepo.twitter4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Rule Builder")
public class RuleBuilderTest {

    @Test
    @DisplayName("Simple Token")
    public void simpleTokenTest() {
        var rule = Rule.builder().withToken("token").applyTag("Token");
        Assertions.assertEquals("token", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }
}
