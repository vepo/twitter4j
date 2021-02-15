package io.vepo.twitter4j.stream;

import java.util.List;
import java.util.Objects;

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
        result = prime * result + Objects.hashCode(mediaKeys);
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
        TweetAttachments other = (TweetAttachments) obj;
        return Objects.equals(mediaKeys, other.mediaKeys);
    }

    @Override
    public String toString() {
        return String.format("TweetAttachments [mediaKeys=%s]", mediaKeys);
    }

}
