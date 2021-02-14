package io.vepo.twitter4j.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Summary {

    private int created;
    
    @JsonProperty("not_created")
    private int notCreated;
    
    private int valid;
    
    private int invalid;

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getNotCreated() {
        return notCreated;
    }

    public void setNotCreated(int notCreated) {
        this.notCreated = notCreated;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getInvalid() {
        return invalid;
    }

    public void setInvalid(int invalid) {
        this.invalid = invalid;
    }

}
