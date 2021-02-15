package io.vepo.twitter4j.stream;

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
        result = prime * result + end;
        result = prime * result + ((normalizedText == null) ? 0 : normalizedText.hashCode());
        result = prime * result + Float.floatToIntBits(probability);
        result = prime * result + start;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        EntityAnnotation other = (EntityAnnotation) obj;
        if (end != other.end)
            return false;
        if (normalizedText == null) {
            if (other.normalizedText != null)
                return false;
        } else if (!normalizedText.equals(other.normalizedText))
            return false;
        if (Float.floatToIntBits(probability) != Float.floatToIntBits(other.probability))
            return false;
        if (start != other.start)
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Annotation [start=" + start + ", end=" + end + ", probability=" + probability + ", type=" + type
                + ", normalizedText=" + normalizedText + "]";
    }

}
