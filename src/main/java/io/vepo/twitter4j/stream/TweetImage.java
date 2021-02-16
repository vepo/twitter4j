package io.vepo.twitter4j.stream;

import java.util.Objects;

public class TweetImage {
    private String url;
    private int width;
    private int height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(height);
        result = prime * result + Integer.hashCode(width);
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
        TweetImage other = (TweetImage) obj;
        return height == other.height &&
                width == other.width &&
                Objects.equals(url, other.url);
    }

    @Override
    public String toString() {
        return String.format("TweetImage [url=%s, width=%s, height=%s]", url, width, height);
    }

}
