package io.vepo.twitter4j;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MediaPublicMetrics {
    @JsonProperty("view_count")
    private int viewCount;

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + viewCount;
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
        MediaPublicMetrics other = (MediaPublicMetrics) obj;
        if (viewCount != other.viewCount)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MediaPublicMetrics [viewCount=" + viewCount + "]";
    }
}
