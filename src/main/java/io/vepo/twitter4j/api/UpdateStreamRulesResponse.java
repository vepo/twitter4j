package io.vepo.twitter4j.api;

import java.util.List;

public class UpdateStreamRulesResponse {
    private Meta meta;
    private List<UpdateStreamRulesError> errors;
    private List<RuleData> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<UpdateStreamRulesError> getErrors() {
        return errors;
    }

    public void setErrors(List<UpdateStreamRulesError> errors) {
        this.errors = errors;
    }

    public List<RuleData> getData() {
        return data;
    }

    public void setData(List<RuleData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AddStreamRulesResponse [meta=" + meta + ", errors=" + errors + ", data=" + data + "]";
    }

}
