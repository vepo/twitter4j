package io.vepo.twitter4j.stream;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetInfoIncludesUserData {
    private String id;
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;
    private String username;
    private String name;
    @JsonProperty("profile_image_url")
    private String profileImageUrl;
    private String description;
    private Boolean verified;
    @JsonProperty("public_metrics")
    private UserPublicMetrics publicMetrics;
    @JsonProperty("protected")
    private Boolean userProtected;
    private String url;
    private String location;
    @JsonProperty("pinned_tweet_id")
    private String pinnedTweetId;
    private UserInfoDataEntities entities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public UserPublicMetrics getPublicMetrics() {
        return publicMetrics;
    }

    public void setPublicMetrics(UserPublicMetrics publicMetrics) {
        this.publicMetrics = publicMetrics;
    }

    public Boolean getUserProtected() {
        return userProtected;
    }

    public void setUserProtected(Boolean userProtected) {
        this.userProtected = userProtected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPinnedTweetId() {
        return pinnedTweetId;
    }

    public void setPinnedTweetId(String pinnedTweetId) {
        this.pinnedTweetId = pinnedTweetId;
    }

    public UserInfoDataEntities getEntities() {
        return entities;
    }

    public void setEntities(UserInfoDataEntities entities) {
        this.entities = entities;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(createdAt);
        result = prime * result + Objects.hashCode(description);
        result = prime * result + Objects.hashCode(entities);
        result = prime * result + Objects.hashCode(id);
        result = prime * result + Objects.hashCode(location);
        result = prime * result + Objects.hashCode(name);
        result = prime * result + Objects.hashCode(pinnedTweetId);
        result = prime * result + Objects.hashCode(profileImageUrl);
        result = prime * result + Objects.hashCode(publicMetrics);
        result = prime * result + Objects.hashCode(url);
        result = prime * result + Objects.hashCode(userProtected);
        result = prime * result + Objects.hashCode(username);
        result = prime * result + Objects.hashCode(verified);
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
        TweetInfoIncludesUserData other = (TweetInfoIncludesUserData) obj;
        return Objects.equals(createdAt, other.createdAt) &&
                Objects.equals(description, other.description) &&
                Objects.equals(entities, other.entities) &&
                Objects.equals(id, other.id) &&
                Objects.equals(location, other.location) &&
                Objects.equals(name, other.name) &&
                Objects.equals(pinnedTweetId, other.pinnedTweetId) &&
                Objects.equals(profileImageUrl, other.profileImageUrl) &&
                Objects.equals(publicMetrics, other.publicMetrics) &&
                Objects.equals(url, other.url) &&
                Objects.equals(userProtected, other.userProtected) &&
                Objects.equals(username, other.username) &&
                Objects.equals(verified, other.verified);

    }

    @Override
    public String toString() {
        return String.format("TweetInfoIncludesUserData [id=%s, createdAt=%s, username=%s, name=%s, profileImageUrl=%s, description=%s, verified=%s, publicMetrics=%s, userProtected=%s, url=%s, location=%s, pinnedTweetId=%s, entities=%s]",
                             id, createdAt, username, name, profileImageUrl, description, verified, publicMetrics,
                             userProtected, url, location, pinnedTweetId, entities);
    }

}
