package io.vepo.twitter4j.stream;

import java.util.Objects;

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(id);
        result = prime * result + Objects.hashCode(tag);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TweetMatchingRule other = (TweetMatchingRule) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(tag, other.tag);
    }

    @Override
    public String toString() {
        return String.format("TweetMatchingRule [id=%s, tag=%s]", id, tag);
    }

}
