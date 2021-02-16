package io.vepo.twitter4j.stream;

import java.util.List;
import java.util.Objects;

public class TweetInfoDataEntities {
    private List<EntityMention> mentions;
    private List<EntityAnnotation> annotations;
    private List<TweetUrl> urls;
    private List<TweetHashtag> hashtags;

    public List<EntityMention> getMentions() {
        return mentions;
    }

    public void setMentions(List<EntityMention> mentions) {
        this.mentions = mentions;
    }

    public List<EntityAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<EntityAnnotation> annotations) {
        this.annotations = annotations;
    }

    public List<TweetUrl> getUrls() {
        return urls;
    }

    public void setUrls(List<TweetUrl> urls) {
        this.urls = urls;
    }

    public List<TweetHashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<TweetHashtag> hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(annotations);
        result = prime * result + Objects.hashCode(hashtags);
        result = prime * result + Objects.hashCode(mentions);
        result = prime * result + Objects.hashCode(urls);
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
        TweetInfoDataEntities other = (TweetInfoDataEntities) obj;
        return Objects.equals(annotations, other.annotations) &&
                Objects.equals(hashtags, other.hashtags) &&
                Objects.equals(mentions, other.mentions) &&
                Objects.equals(urls, other.urls);
    }

    @Override
    public String toString() {
        return String.format("TweetInfoDataEntities [mentions=%s, annotations=%s, urls=%s, hashtags=%s]", mentions,
                             annotations, urls, hashtags);
    }

}
