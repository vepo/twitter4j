package io.vepo.twitter4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.vepo.twitter4j.Rule.Language;
import io.vepo.twitter4j.Rule.Matching;

@DisplayName("Rule Builder")
class RuleBuilderTest {

    @Test
    @DisplayName("Many Token")
    void andTokenTest() {
        var rule = Rule.builder()
                       .withToken("token")
                       .withImages()
                       .withMedia()
                       .applyTag("Token");
        Assertions.assertEquals("token has:images has:media", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }

    @Test
    @DisplayName("Composed Token")
    void composeedTokenTest() {
        var rule = Rule.builder()
                       .withToken("token with space")
                       .applyTag("Token");
        Assertions.assertEquals("\"token with space\"", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }

    @Test
    @DisplayName("Token + Group")
    void groupTokenTest() {
        var rule = Rule.builder()
                       .withToken("token")
                       .with(Matching.builder()
                                     .withImages()
                                     .or()
                                     .withLinks()
                                     .build())
                       .isRetweet()
                       .applyTag("Token");
        Assertions.assertEquals("token (has:images OR has:links) is:retweet", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());

        rule = Rule.builder()
                   .withToken("token")
                   .with(Matching.builder()
                                 .withImages()
                                 .or()
                                 .withLinks()
                                 .or()
                                 .isRetweet()
                                 .build())
                   .applyTag("Token");
        Assertions.assertEquals("token (has:images OR has:links OR is:retweet)", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }

    @Test
    @DisplayName("Token + Languages")
    void langsAndTokenTest() {
        var rule = Rule.builder()
                       .withToken("token")
                       .without(Matching.builder()
                                        .withLanguage(Language.Portuguese)
                                        .or()
                                        .withLanguage(Language.English)
                                        .build())
                       .applyTag("Token");
        Assertions.assertEquals("token -(lang:pt OR lang:en)", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());

        rule = Rule.builder()
                   .withToken("token")
                   .withLanguage(Language.Portuguese)
                   .or()
                   .withLanguage(Language.English)
                   .applyTag("Token");
        Assertions.assertEquals("token lang:pt OR lang:en", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }

    @Test
    @DisplayName("Many Token with OR")
    void orTokenTest() {
        var rule = Rule.builder()
                       .withToken("token")
                       .or()
                       .withMentions()
                       .or()
                       .withMedia()
                       .applyTag("Token");
        Assertions.assertEquals("token OR has:mentions OR has:media", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }

    @Test
    @DisplayName("Root Group")
    void rootGroupTest() {
        var rule = Rule.builder()
                       .withToken("root")
                       .with(Matching.builder()
                                     .withMedia()
                                     .or()
                                     .withMentions()
                                     .or()
                                     .withToken("token")
                                     .build())
                       .applyTag("Token");
        Assertions.assertEquals("root (has:media OR has:mentions OR token)", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }

    @Test
    @DisplayName("Simple Token")
    void simpleTokenTest() {
        var rule = Rule.builder()
                       .withToken("token")
                       .applyTag("Token");
        Assertions.assertEquals("token", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }

    @Test
    @DisplayName("Sub Group")
    void subGroupTest() {
        var rule = Rule.builder()
                       .withToken("root")
                       .with(Matching.builder()
                                     .withMedia()
                                     .or()
                                     .withMentions()
                                     .or()
                                     .withToken("token")
                                     .without(Matching.builder()
                                                      .withImages()
                                                      .build())
                                     .build())
                       .applyTag("Token");
        Assertions.assertEquals("root (has:media OR has:mentions OR token -(has:images))", rule.getValue());
        Assertions.assertEquals("Token", rule.getTag());
    }
}
