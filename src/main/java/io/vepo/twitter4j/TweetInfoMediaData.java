package io.vepo.twitter4j;

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
        result = prime * result + height;
        result = prime * result + ((mediaKey == null) ? 0 : mediaKey.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + width;
        result = prime * result + ((previewImageUrl == null) ? 0 : previewImageUrl.hashCode());
        result = prime * result + ((durationMs == null) ? 0 : durationMs.hashCode());
        result = prime * result + ((publicMetrics == null) ? 0 : publicMetrics.hashCode());
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
        TweetInfoMediaData other = (TweetInfoMediaData) obj;
        if (height != other.height)
            return false;
        if (mediaKey == null) {
            if (other.mediaKey != null)
                return false;
        } else if (!mediaKey.equals(other.mediaKey))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        if (width != other.width)
            return false;
        if (previewImageUrl == null) {
            if (other.previewImageUrl != null)
                return false;
        } else if (!previewImageUrl.equals(other.previewImageUrl))
            return false;
        if (durationMs == null) {
            if (other.durationMs != null)
                return false;
        } else if (!durationMs.equals(other.durationMs))
            return false;
        if (publicMetrics == null) {
            if (other.publicMetrics != null)
                return false;
        } else if (!publicMetrics.equals(other.publicMetrics))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TweetInfoMediaData [mediaKey=" + mediaKey + ", type=" + type + ", height=" + height + ", width=" + width
                + ", url=" + url + ", previewImageUrl=" + previewImageUrl + ", durationMs=" + durationMs
                + ", publicMetrics=" + publicMetrics + "]";
    }

}
