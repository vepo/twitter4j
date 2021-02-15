package io.vepo.twitter4j;

import java.util.List;

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
        result = prime * result + ((annotations == null) ? 0 : annotations.hashCode());
        result = prime * result + ((hashtags == null) ? 0 : hashtags.hashCode());
        result = prime * result + ((mentions == null) ? 0 : mentions.hashCode());
        result = prime * result + ((urls == null) ? 0 : urls.hashCode());
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
        TweetInfoDataEntities other = (TweetInfoDataEntities) obj;
        if (annotations == null) {
            if (other.annotations != null)
                return false;
        } else if (!annotations.equals(other.annotations))
            return false;
        if (hashtags == null) {
            if (other.hashtags != null)
                return false;
        } else if (!hashtags.equals(other.hashtags))
            return false;
        if (mentions == null) {
            if (other.mentions != null)
                return false;
        } else if (!mentions.equals(other.mentions))
            return false;
        if (urls == null) {
            if (other.urls != null)
                return false;
        } else if (!urls.equals(other.urls))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TweetInfoDataEntities [mentions=" + mentions + ", annotations=" + annotations + ", urls=" + urls
                + ", hashtags=" + hashtags + "]";
    }

}
