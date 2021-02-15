package io.vepo.twitter4j.stream;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GeoData {
    private String type;
    private String name;
    private List<Double> bbox;
    private Map<String, String> properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getBbox() {
        return bbox;
    }

    public void setBbox(List<Double> bbox) {
        this.bbox = bbox;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(bbox);
        result = prime * result + Objects.hashCode(name);
        result = prime * result + Objects.hashCode(properties);
        result = prime * result + Objects.hashCode(type);
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
        GeoData other = (GeoData) obj;
        return Objects.equals(bbox, other.bbox) &&
                Objects.equals(name, other.name) &&
                Objects.equals(properties, other.properties) &&
                Objects.equals(type, other.type);
    }

    @Override
    public String toString() {
        return String.format("GeoData [type=%s, name=%s, bbox=%s, properties=%s]", type, name, bbox, properties);
    }

}
