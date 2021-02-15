package io.vepo.twitter4j;

import java.util.Date;

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
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((entities == null) ? 0 : entities.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pinnedTweetId == null) ? 0 : pinnedTweetId.hashCode());
        result = prime * result + ((profileImageUrl == null) ? 0 : profileImageUrl.hashCode());
        result = prime * result + ((publicMetrics == null) ? 0 : publicMetrics.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((userProtected == null) ? 0 : userProtected.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((verified == null) ? 0 : verified.hashCode());
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
        TweetInfoIncludesUserData other = (TweetInfoIncludesUserData) obj;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
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
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (pinnedTweetId == null) {
            if (other.pinnedTweetId != null)
                return false;
        } else if (!pinnedTweetId.equals(other.pinnedTweetId))
            return false;
        if (profileImageUrl == null) {
            if (other.profileImageUrl != null)
                return false;
        } else if (!profileImageUrl.equals(other.profileImageUrl))
            return false;
        if (publicMetrics == null) {
            if (other.publicMetrics != null)
                return false;
        } else if (!publicMetrics.equals(other.publicMetrics))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        if (userProtected == null) {
            if (other.userProtected != null)
                return false;
        } else if (!userProtected.equals(other.userProtected))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (verified == null) {
            if (other.verified != null)
                return false;
        } else if (!verified.equals(other.verified))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TweetInfoIncludesUserData [id=" + id + ", createdAt=" + createdAt + ", username=" + username + ", name="
                + name + ", profileImageUrl=" + profileImageUrl + ", description=" + description + ", verified="
                + verified + ", publicMetrics=" + publicMetrics + ", userProtected=" + userProtected + ", url=" + url
                + ", location=" + location + ", pinnedTweetId=" + pinnedTweetId + ", entities=" + entities + "]";
    }

}
