package io.vepo.twitter4j.stream;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetInfoPlaceData {
    @JsonProperty("full_name")
    private String fullName;
    private String name;
    private GeoData geo;
    private String id;
    @JsonProperty("place_type")
    private String placeType;
    @JsonProperty("country_code")
    private String countryCode;
    private String country;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoData getGeo() {
        return geo;
    }

    public void setGeo(GeoData geo) {
        this.geo = geo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(country);
        result = prime * result + Objects.hashCode(countryCode);
        result = prime * result + Objects.hashCode(fullName);
        result = prime * result + Objects.hashCode(geo);
        result = prime * result + Objects.hashCode(id);
        result = prime * result + Objects.hashCode(name);
        result = prime * result + Objects.hashCode(placeType);
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
        TweetInfoPlaceData other = (TweetInfoPlaceData) obj;
        return Objects.equals(country, other.country) &&
                Objects.equals(countryCode, other.countryCode) &&
                Objects.equals(fullName, other.fullName) &&
                Objects.equals(geo, other.geo) &&
                Objects.equals(id, other.id) &&
                Objects.equals(name, other.name) &&
                Objects.equals(placeType, other.placeType);
    }

    @Override
    public String toString() {
        return String.format("TweetInfoPlaceData [fullName=%s, name=%s, geo=%s, id=%s, placeType=%s, countryCode=%s, country=%s]",
                             fullName, name, geo, id, placeType, countryCode, country);
    }

}
