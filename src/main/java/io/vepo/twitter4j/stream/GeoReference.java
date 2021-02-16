package io.vepo.twitter4j.stream;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoReference {
    @JsonProperty("place_id")
    private String placeId;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(placeId);
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
        GeoReference other = (GeoReference) obj;
        return Objects.equals(placeId, other.placeId);
    }

    @Override
    public String toString() {
        return String.format("GeoReference [placeId=%s]", placeId);
    }
}
