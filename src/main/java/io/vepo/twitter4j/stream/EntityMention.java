package io.vepo.twitter4j.stream;

import java.util.Objects;

public class EntityMention {

    private String id;
    private int start;
    private int end;
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        return Objects.hash(this.id, this.start, this.end, this.username);
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
        return Objects.equals(id, other.id) &&
                end == other.end &&
                start == other.start &&
                Objects.equals(username, other.username);
    }

    @Override
    public String toString() {
        return String.format("EntityMention [id=%s, start=%s, end=%s, username=%s]", id, start, end, username);
    }
}
