package io.vepo.twitter4j;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetAttachments {

    @JsonProperty("media_keys")
    private List<String> mediaKeys;

    public List<String> getMediaKeys() {
        return mediaKeys;
    }

    public void setMediaKeys(List<String> mediaKeys) {
        this.mediaKeys = mediaKeys;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mediaKeys == null) ? 0 : mediaKeys.hashCode());
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
        TweetAttachments other = (TweetAttachments) obj;
        if (mediaKeys == null) {
            if (other.mediaKeys != null)
                return false;
        } else if (!mediaKeys.equals(other.mediaKeys))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TweetAttachments [mediaKeys=" + mediaKeys + "]";
    }

}
