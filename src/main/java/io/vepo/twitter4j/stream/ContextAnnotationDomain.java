package io.vepo.twitter4j.stream;

import java.util.Objects;

public class ContextAnnotationDomain {

    private String id;
    private String name;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(description);
        result = prime * result + Objects.hashCode(id);
        result = prime * result + Objects.hashCode(name);
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
        ContextAnnotationDomain other = (ContextAnnotationDomain) obj;
        return Objects.equals(description, other.description) &&
                Objects.equals(name, other.name) &&
                Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return String.format("ContextAnnotationDomain [id=%s, name=%s, description=%s]", id, name, description);
    }

}
