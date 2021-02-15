package io.vepo.twitter4j.stream;

import java.util.Objects;

public class ReferencedTweet {
    private String type;
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(id);
        result = prime * result + Objects.hashCode(type);
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
        ReferencedTweet other = (ReferencedTweet) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(type, other.type);
    }

    @Override
    public String toString() {
        return String.format("ReferencedTweet [type=%s, id=%s]", type, id);
    }

}
