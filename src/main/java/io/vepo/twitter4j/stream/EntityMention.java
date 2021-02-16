package io.vepo.twitter4j.stream;

import java.util.Objects;

public class EntityMention {

    private int start;
    private int end;
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(end);
        result = prime * result + Integer.hashCode(start);
        result = prime * result + Objects.hashCode(username);
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
        EntityMention other = (EntityMention) obj;
        return end == other.end &&
                start == other.start &&
                Objects.equals(username, other.username);
    }

    @Override
    public String toString() {
        return String.format("EntityMention [start=%s, end=%s, username=%s]", start, end, username);
    }
}
