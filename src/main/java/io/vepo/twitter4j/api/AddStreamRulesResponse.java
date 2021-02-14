package io.vepo.twitter4j.api;

import java.util.List;

public class AddStreamRulesResponse {
    private Meta meta;
    private List<AddStreamRulesError> errors;
    private List<AddStreamRulesData> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<AddStreamRulesError> getErrors() {
        return errors;
    }

    public void setErrors(List<AddStreamRulesError> errors) {
        this.errors = errors;
    }

    public List<AddStreamRulesData> getData() {
        return data;
    }

    public void setData(List<AddStreamRulesData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AddStreamRulesResponse [meta=" + meta + ", errors=" + errors + ", data=" + data + "]";
    }

}
