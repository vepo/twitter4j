package io.vepo.twitter4j.stream;

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
        result = prime * result + ((domain == null) ? 0 : domain.hashCode());
        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
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
        ContextAnnotation other = (ContextAnnotation) obj;
        if (domain == null) {
            if (other.domain != null)
                return false;
        } else if (!domain.equals(other.domain))
            return false;
        if (entity == null) {
            if (other.entity != null)
                return false;
        } else if (!entity.equals(other.entity))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ContextAnnotation [domain=" + domain + ", entity=" + entity + "]";
    }

}
