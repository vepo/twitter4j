package io.vepo.twitter4j.stream;

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
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserInfoDataEntities other = (UserInfoDataEntities) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserInfoDataEntities [url=" + url + ", description=" + description + "]";
    }

}
