package io.vepo.twitter4j.stream;

import java.util.Objects;

public class TweetHashtag {
    private int start;
    private int end;
    private String tag;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(end);
        result = prime * result + Integer.hashCode(start);
        result = prime * result + Objects.hashCode(tag);
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
        TweetHashtag other = (TweetHashtag) obj;
        return end == other.end &&
                start == other.start &&
                Objects.equals(tag, other.tag);
    }

    @Override
    public String toString() {
        return String.format("TweetHashtag [start=%s, end=%s, tag=%s]", start, end, tag);
    }

}
