package io.vepo.twitter4j.impl.api;

import java.util.List;

public class GetStreamRulesResponse {
    private Meta meta;
    private List<RuleData> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<RuleData> getData() {
        return data;
    }

    public void setData(List<RuleData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetStreamRulesResponse [meta=" + meta + ", data=" + data + "]";
    }

}
