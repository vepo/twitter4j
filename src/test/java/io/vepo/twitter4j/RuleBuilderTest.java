package io.vepo.twitter4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Rule Builder")
class RuleBuilderTest {

    @Test
    @DisplayName("Simple Token")
    void simpleTokenTest() {
        var rule = Rule.builder().withToken("token").applyTag("Token");
        Assertions.assertEquals("token", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }

    @Test
    @DisplayName("Token + Group")
    void groupTokenTest() {
        var rule = Rule.builder()
                       .withToken("token")
                       .group()
                           .withImages()
                           .or()
                           .withLinks()
                       .end()
                       .applyTag("Token");
        Assertions.assertEquals("token (has:images OR has:links)", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }
}
