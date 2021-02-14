package io.vepo.twitter4j.api;

import java.util.List;

import io.vepo.twitter4j.Rule;

public class AddStreamRulesRequest {
    private List<Rule> add;

    public AddStreamRulesRequest(List<Rule> add) {
        this.add = add;
    }

    public List<Rule> getAdd() {
        return add;
    }

    public void setAdd(List<Rule> add) {
        this.add = add;
    }
}
