package io.vepo.twitter4j.stream;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetInfoMediaData {
    @JsonProperty("media_key")
    private String mediaKey;
    private String type;
    private int height;
    private int width;
    private String url;
    @JsonProperty("preview_image_url")
    private String previewImageUrl;
    @JsonProperty("duration_ms")
    private Integer durationMs;
    @JsonProperty("public_metrics")
    private MediaPublicMetrics publicMetrics;

    public String getMediaKey() {
        return mediaKey;
    }

    public void setMediaKey(String mediaKey) {
        this.mediaKey = mediaKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPreviewImageUrl() {
        return previewImageUrl;
    }

    public void setPreviewImageUrl(String previewImageUrl) {
        this.previewImageUrl = previewImageUrl;
    }

    public Integer getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    public MediaPublicMetrics getPublicMetrics() {
        return publicMetrics;
    }

    public void setPublicMetrics(MediaPublicMetrics publicMetrics) {
        this.publicMetrics = publicMetrics;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(height);
        result = prime * result + Integer.hashCode(width);
        result = prime * result + Objects.hashCode(mediaKey);
        result = prime * result + Objects.hashCode(type);
        result = prime * result + Objects.hashCode(url);
        result = prime * result + Objects.hashCode(previewImageUrl);
        result = prime * result + Objects.hashCode(durationMs);
        result = prime * result + Objects.hashCode(publicMetrics);
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
        TweetInfoMediaData other = (TweetInfoMediaData) obj;
        return height == other.height &&
                width == other.width &&
                Objects.equals(mediaKey, other.mediaKey) &&
                Objects.equals(type, other.type) &&
                Objects.equals(url, other.url) &&
                Objects.equals(previewImageUrl, other.previewImageUrl) &&
                Objects.equals(durationMs, other.durationMs) &&
                Objects.equals(publicMetrics, other.publicMetrics);
    }

    @Override
    public String toString() {
        return String.format("TweetInfoMediaData [mediaKey=%s, type=%s, height=%s, width=%s, url=%s, previewImageUrl=%s, durationMs=%s, publicMetrics=%s]",
                             mediaKey, type, height, width, url, previewImageUrl, durationMs, publicMetrics);
    }

}
