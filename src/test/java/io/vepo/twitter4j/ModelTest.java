package io.vepo.twitter4j;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jparams.verifier.tostring.ToStringVerifier;

import io.vepo.twitter4j.stream.TweetInfo;
import io.vepo.twitter4j.stream.TweetInfoData;
import io.vepo.twitter4j.stream.TweetInfoIncludes;
import io.vepo.twitter4j.stream.TweetInfoIncludesUserData;
import io.vepo.twitter4j.stream.TweetMatchingRule;
import nl.jqno.equalsverifier.EqualsVerifier;

@DisplayName("Test Model")
class ModelTest {

    @ParameterizedTest
    @ValueSource(classes = {
        Rule.class,
        TweetInfo.class,
        TweetInfoData.class,
        TweetInfoIncludes.class,
        TweetMatchingRule.class,
        TweetInfoIncludesUserData.class
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
        TweetInfo.class,
        TweetInfoData.class,
        TweetInfoIncludes.class,
        TweetMatchingRule.class,
        TweetInfoIncludesUserData.class
    })
    @DisplayName("Check toString: {0}")
    void toStringTest(Class<?> modelClass) {
        ToStringVerifier.forClass(modelClass)
                        .verify();
    }

    @ParameterizedTest
    @MethodSource("provideStreamSamplesFiles")
    @DisplayName("Check if it can read some real world input")
    void serialziationTest(File file) throws JsonMappingException, JsonProcessingException, IOException {
        var mapper = new ObjectMapper();
        var value = mapper.readValue(Files.readString(file.toPath()), TweetInfo.class);
        assertNotNull(value);
    }

    private static Stream<Arguments> provideStreamSamplesFiles() throws IOException {
        return Files.list(Paths.get(".", "reference", "stream"))
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .map(file -> Arguments.of(file));
    }
}
