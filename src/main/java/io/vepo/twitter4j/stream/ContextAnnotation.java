package io.vepo.twitter4j.stream;

import java.util.Objects;

public class ContextAnnotation {

    private ContextAnnotationDomain domain;
    private ContextAnnotationEntity entity;

    public ContextAnnotationDomain getDomain() {
        return domain;
    }

    public void setDomain(ContextAnnotationDomain domain) {
        this.domain = domain;
    }

    public ContextAnnotationEntity getEntity() {
        return entity;
    }

    public void setEntity(ContextAnnotationEntity entity) {
        this.entity = entity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(domain);
        result = prime * result + Objects.hashCode(entity);
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
        ContextAnnotation other = (ContextAnnotation) obj;
        return Objects.equals(domain, other.domain) &&
                Objects.equals(entity, other.entity);
    }

    @Override
    public String toString() {
        return String.format("ContextAnnotation [domain=%s, entity=%s]", domain, entity);
    }

}
