package io.vepo.twitter4j;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetInfoData {
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;
    private String id;
    private String text;
    @JsonProperty("author_id")
    private String authorId;
    @JsonProperty("conversation_id")
    private String conversationId;
    @JsonProperty("context_annotations")
    private List<ContextAnnotation> contextAnnotations;
    private String source;
    @JsonProperty("possibly_sensitive")
    private Boolean possiblySensitive;
    private TweetInfoDataEntities entities;
    @JsonProperty("referenced_tweets")
    private List<ReferencedTweet> referencedTweets;
    @JsonProperty("public_metrics")
    private TweetPublicMetrics publicMetrics;
    private String lang;
    @JsonProperty("reply_settings")
    private String replySettings;
    private TweetAttachments attachments;
    private GeoReference geo;
    @JsonProperty("in_reply_to_user_id")
    private String inReplyToUserId;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public List<ContextAnnotation> getContextAnnotations() {
        return contextAnnotations;
    }

    public void setContextAnnotations(List<ContextAnnotation> contextAnnotations) {
        this.contextAnnotations = contextAnnotations;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getPossiblySensitive() {
        return possiblySensitive;
    }

    public void setPossiblySensitive(Boolean possiblySensitive) {
        this.possiblySensitive = possiblySensitive;
    }

    public TweetInfoDataEntities getEntities() {
        return entities;
    }

    public void setEntities(TweetInfoDataEntities entities) {
        this.entities = entities;
    }

    public List<ReferencedTweet> getReferencedTweets() {
        return referencedTweets;
    }

    public void setReferencedTweets(List<ReferencedTweet> referencedTweets) {
        this.referencedTweets = referencedTweets;
    }

    public TweetPublicMetrics getPublicMetrics() {
        return publicMetrics;
    }

    public void setPublicMetrics(TweetPublicMetrics publicMetrics) {
        this.publicMetrics = publicMetrics;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getReplySettings() {
        return replySettings;
    }

    public void setReplySettings(String replySettings) {
        this.replySettings = replySettings;
    }

    public TweetAttachments getAttachments() {
        return attachments;
    }

    public void setAttachments(TweetAttachments attachments) {
        this.attachments = attachments;
    }

    public GeoReference getGeo() {
        return geo;
    }

    public void setGeo(GeoReference geo) {
        this.geo = geo;
    }

    public String getInReplyToUserId() {
        return inReplyToUserId;
    }

    public void setInReplyToUserId(String inReplyToUserId) {
        this.inReplyToUserId = inReplyToUserId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((attachments == null) ? 0 : attachments.hashCode());
        result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
        result = prime * result + ((contextAnnotations == null) ? 0 : contextAnnotations.hashCode());
        result = prime * result + ((conversationId == null) ? 0 : conversationId.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((entities == null) ? 0 : entities.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lang == null) ? 0 : lang.hashCode());
        result = prime * result + ((possiblySensitive == null) ? 0 : possiblySensitive.hashCode());
        result = prime * result + ((publicMetrics == null) ? 0 : publicMetrics.hashCode());
        result = prime * result + ((referencedTweets == null) ? 0 : referencedTweets.hashCode());
        result = prime * result + ((replySettings == null) ? 0 : replySettings.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((geo == null) ? 0 : geo.hashCode());
        result = prime * result + ((inReplyToUserId == null) ? 0 : inReplyToUserId.hashCode());
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
        TweetInfoData other = (TweetInfoData) obj;
        if (attachments == null) {
            if (other.attachments != null)
                return false;
        } else if (!attachments.equals(other.attachments))
            return false;
        if (authorId == null) {
            if (other.authorId != null)
                return false;
        } else if (!authorId.equals(other.authorId))
            return false;
        if (contextAnnotations == null) {
            if (other.contextAnnotations != null)
                return false;
        } else if (!contextAnnotations.equals(other.contextAnnotations))
            return false;
        if (conversationId == null) {
            if (other.conversationId != null)
                return false;
        } else if (!conversationId.equals(other.conversationId))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (entities == null) {
            if (other.entities != null)
                return false;
        } else if (!entities.equals(other.entities))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lang == null) {
            if (other.lang != null)
                return false;
        } else if (!lang.equals(other.lang))
            return false;
        if (possiblySensitive == null) {
            if (other.possiblySensitive != null)
                return false;
        } else if (!possiblySensitive.equals(other.possiblySensitive))
            return false;
        if (publicMetrics == null) {
            if (other.publicMetrics != null)
                return false;
        } else if (!publicMetrics.equals(other.publicMetrics))
            return false;
        if (referencedTweets == null) {
            if (other.referencedTweets != null)
                return false;
        } else if (!referencedTweets.equals(other.referencedTweets))
            return false;
        if (replySettings == null) {
            if (other.replySettings != null)
                return false;
        } else if (!replySettings.equals(other.replySettings))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (geo == null) {
            if (other.geo != null)
                return false;
        } else if (!geo.equals(other.geo))
            return false;
        if (inReplyToUserId == null) {
            if (other.inReplyToUserId != null)
                return false;
        } else if (!inReplyToUserId.equals(other.inReplyToUserId))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "TweetInfoData [createdAt=" + createdAt + ", id=" + id + ", text=" + text + ", authorId=" + authorId
                + ", conversationId=" + conversationId + ", contextAnnotations=" + contextAnnotations + ", source="
                + source + ", possiblySensitive=" + possiblySensitive + ", entities=" + entities + ", referencedTweets="
                + referencedTweets + ", publicMetrics=" + publicMetrics + ", lang=" + lang + ", replySettings="
                + replySettings + ", attachments=" + attachments + ", geo=" + geo + ", inReplyToUserId="
                + inReplyToUserId + "]";
    }

}
