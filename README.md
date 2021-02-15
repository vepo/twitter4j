# Twitter 4J

WIP - Work in Progress

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=vepo_twitter4j&metric=alert_status)](https://sonarcloud.io/dashboard?id=vepo_twitter4j) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=vepo_twitter4j&metric=coverage)](https://sonarcloud.io/dashboard?id=vepo_twitter4j)

Example: 

```java
TwitterClient tClient = new TwitterClient(System.getenv("API_KEY"),
                                            System.getenv("API_SECRET_KEY"));
tClient.authenticate()
        .stream()
        .with(Rule.builder()
                    .withToken("Trump")
                    .without(Matching.builder()
                                     .withImages()
                                     .or()
                                     .isRetweet()
                                     .build())
                    .applyTag("Tweets about Trump"))
        .consume(System.out::println)
        .join();
```