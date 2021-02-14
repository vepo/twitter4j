# Twitter 4J

WIP - Work in Progress


Example: 

```java
TwitterClient tClient = new TwitterClient(System.getenv("API_KEY"),
                                            System.getenv("API_SECRET_KEY"));
tClient.authenticate()
        .stream()
        .with(Rule.builder()
                    .withToken("Trump")
                    .without()
                    .withImages()
                    .endGroup()
                    .applyTag("Tweets about Trump"))
        .consume(System.out::println);
```