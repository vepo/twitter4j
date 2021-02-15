package io.vepo.twitter4j.stream;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityAnnotation {

    private int start;
    private int end;
    private float probability;
    private String type;
    @JsonProperty("normalized_text")
    private String normalizedText;

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

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNormalizedText() {
        return normalizedText;
    }

    public void setNormalizedText(String normalizedText) {
        this.normalizedText = normalizedText;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(end);
        result = prime * result + Objects.hashCode(normalizedText);
        result = prime * result + Float.hashCode(probability);
        result = prime * result + Integer.hashCode(start);
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
        EntityAnnotation other = (EntityAnnotation) obj;
        return end == other.end &&
                start == other.start &&
                Float.compare(probability, other.probability) == 0 &&
                Objects.equals(normalizedText, other.normalizedText) &&
                Objects.equals(type, other.type);
    }

    @Override
    public String toString() {
        return String.format("EntityAnnotation [start=%s, end=%s, probability=%s, type=%s, normalizedText=%s]", start,
                             end, probability, type, normalizedText);
    }

}
