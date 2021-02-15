package io.vepo.twitter4j.stream;

import java.util.List;
import java.util.Objects;

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
        result = prime * result + Integer.hashCode(start);
        result = prime * result + Integer.hashCode(end);
        result = prime * result + Objects.hashCode(description);
        result = prime * result + Objects.hashCode(displayUrl);
        result = prime * result + Objects.hashCode(expandedUrl);
        result = prime * result + Objects.hashCode(images);
        result = prime * result + Objects.hashCode(status);
        result = prime * result + Objects.hashCode(title);
        result = prime * result + Objects.hashCode(unwoundUrl);
        result = prime * result + Objects.hashCode(url);
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

        TweetUrl other = (TweetUrl) obj;
        return start == other.start &&
                end == other.end &&
                Objects.equals(description, other.description) &&
                Objects.equals(displayUrl, other.displayUrl) &&
                Objects.equals(expandedUrl, other.expandedUrl) &&
                Objects.equals(images, other.images) &&
                Objects.equals(status, other.status) &&
                Objects.equals(title, other.title) &&
                Objects.equals(unwoundUrl, other.unwoundUrl) &&
                Objects.equals(url, other.url);
    }

    @Override
    public String toString() {
        return String.format("TweetUrl [start=%s, end=%s, url=%s, expandedUrl=%s, displayUrl=%s, images=%s, status=%s, title=%s, description=%s, unwoundUrl=%s]",
                             start, end, url, expandedUrl, displayUrl, images, status, title, description, unwoundUrl);
    }

   
}
