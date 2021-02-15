package io.vepo.twitter4j.stream;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        result = prime * result + Objects.hashCode(attachments);
        result = prime * result + Objects.hashCode(authorId);
        result = prime * result + Objects.hashCode(contextAnnotations);
        result = prime * result + Objects.hashCode(conversationId);
        result = prime * result + Objects.hashCode(createdAt);
        result = prime * result + Objects.hashCode(entities);
        result = prime * result + Objects.hashCode(id);
        result = prime * result + Objects.hashCode(lang);
        result = prime * result + Objects.hashCode(possiblySensitive);
        result = prime * result + Objects.hashCode(publicMetrics);
        result = prime * result + Objects.hashCode(referencedTweets);
        result = prime * result + Objects.hashCode(replySettings);
        result = prime * result + Objects.hashCode(source);
        result = prime * result + Objects.hashCode(text);
        result = prime * result + Objects.hashCode(geo);
        result = prime * result + Objects.hashCode(inReplyToUserId);
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
        TweetInfoData other = (TweetInfoData) obj;
        return Objects.equals(attachments, other.attachments) &&
                Objects.equals(authorId, other.authorId) &&
                Objects.equals(contextAnnotations, other.contextAnnotations) &&
                Objects.equals(conversationId, other.conversationId) &&
                Objects.equals(createdAt, other.createdAt) &&
                Objects.equals(entities, other.entities) &&
                Objects.equals(id, other.id) &&
                Objects.equals(lang, other.lang) &&
                Objects.equals(possiblySensitive, other.possiblySensitive) &&
                Objects.equals(publicMetrics, other.publicMetrics) &&
                Objects.equals(referencedTweets, other.referencedTweets) &&
                Objects.equals(replySettings, other.replySettings) &&
                Objects.equals(source, other.source) &&
                Objects.equals(text, other.text) &&
                Objects.equals(geo, other.geo) &&
                Objects.equals(inReplyToUserId, other.inReplyToUserId);
    }

    @Override
    public String toString() {
        return String.format("TweetInfoData [createdAt=%s, id=%s, text=%s, authorId=%s, conversationId=%s, contextAnnotations=%s, source=%s, possiblySensitive=%s, entities=%s, referencedTweets=%s, publicMetrics=%s, lang=%s, replySettings=%s, attachments=%s, geo=%s, inReplyToUserId=%s]",
                             createdAt, id, text, authorId, conversationId, contextAnnotations, source,
                             possiblySensitive, entities, referencedTweets, publicMetrics, lang, replySettings,
                             attachments, geo, inReplyToUserId);
    }

}
