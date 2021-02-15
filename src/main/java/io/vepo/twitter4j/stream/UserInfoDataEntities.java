package io.vepo.twitter4j.stream;

import java.util.Objects;

public class UserInfoDataEntities {
    private TweetInfoDataEntities url;
    private TweetInfoDataEntities description;

    public TweetInfoDataEntities getUrl() {
        return url;
    }

    public void setUrl(TweetInfoDataEntities url) {
        this.url = url;
    }

    public TweetInfoDataEntities getDescription() {
        return description;
    }

    public void setDescription(TweetInfoDataEntities description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(description);
        result = prime * result + Objects.hashCode(url);
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
        UserInfoDataEntities other = (UserInfoDataEntities) obj;
        return Objects.equals(description, other.description) &&
                Objects.equals(url, other.url);
    }

    @Override
    public String toString() {
        return String.format("UserInfoDataEntities [url=%s, description=%s]", url, description);
    }

}
