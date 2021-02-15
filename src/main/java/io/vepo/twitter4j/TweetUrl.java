package io.vepo.twitter4j;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetUrl {
    private int start;
    private int end;
    private String url;
    @JsonProperty("expanded_url")
    private String expandedUrl;
    @JsonProperty("display_url")
    private String displayUrl;
    private List<TweetImage> images;
    private Integer status;
    private String title;
    private String description;
    @JsonProperty("unwound_url")
    private String unwoundUrl;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public void setExpandedUrl(String expandedUrl) {
        this.expandedUrl = expandedUrl;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public List<TweetImage> getImages() {
        return images;
    }

    public void setImages(List<TweetImage> images) {
        this.images = images;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnwoundUrl() {
        return unwoundUrl;
    }

    public void setUnwoundUrl(String unwoundUrl) {
        this.unwoundUrl = unwoundUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((displayUrl == null) ? 0 : displayUrl.hashCode());
        result = prime * result + end;
        result = prime * result + ((expandedUrl == null) ? 0 : expandedUrl.hashCode());
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + start;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((unwoundUrl == null) ? 0 : unwoundUrl.hashCode());
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
        TweetUrl other = (TweetUrl) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (displayUrl == null) {
            if (other.displayUrl != null)
                return false;
        } else if (!displayUrl.equals(other.displayUrl))
            return false;
        if (end != other.end)
            return false;
        if (expandedUrl == null) {
            if (other.expandedUrl != null)
                return false;
        } else if (!expandedUrl.equals(other.expandedUrl))
            return false;
        if (images == null) {
            if (other.images != null)
                return false;
        } else if (!images.equals(other.images))
            return false;
        if (start != other.start)
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (unwoundUrl == null) {
            if (other.unwoundUrl != null)
                return false;
        } else if (!unwoundUrl.equals(other.unwoundUrl))
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
        return "TweetUrl [start=" + start + ", end=" + end + ", url=" + url + ", expandedUrl=" + expandedUrl
                + ", displayUrl=" + displayUrl + ", images=" + images + ", status=" + status + ", title=" + title
                + ", description=" + description + ", unwoundUrl=" + unwoundUrl + "]";
    }

}
