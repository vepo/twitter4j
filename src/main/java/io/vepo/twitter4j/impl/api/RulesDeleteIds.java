package io.vepo.twitter4j.impl.api;

import java.util.List;

public class RulesDeleteIds {
    private List<String> ids;

    public RulesDeleteIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "RulesDeleteIds [ids=" + ids + "]";
    }
}
