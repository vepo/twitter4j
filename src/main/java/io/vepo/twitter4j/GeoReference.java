package io.vepo.twitter4j;

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
        result = prime * result + ((placeId == null) ? 0 : placeId.hashCode());
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
        GeoReference other = (GeoReference) obj;
        if (placeId == null) {
            if (other.placeId != null)
                return false;
        } else if (!placeId.equals(other.placeId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GeoData [placeId=" + placeId + "]";
    }
}
