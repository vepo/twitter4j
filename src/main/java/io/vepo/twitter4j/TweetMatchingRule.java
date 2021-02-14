package io.vepo.twitter4j;

public class TweetMatchingRule {
    private String id;
    private String tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TweetMatchingRule [id=" + id + ", tag=" + tag + "]";
    }

}
