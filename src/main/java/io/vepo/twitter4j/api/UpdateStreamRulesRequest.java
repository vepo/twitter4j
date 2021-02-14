package io.vepo.twitter4j.api;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.vepo.twitter4j.Rule;

@JsonInclude(NON_EMPTY)
public class UpdateStreamRulesRequest {
    private List<Rule> add;
    private RulesDeleteIds delete;

    public UpdateStreamRulesRequest(List<Rule> add) {
        this.add = add;
    }

    public UpdateStreamRulesRequest(RulesDeleteIds delete) {
        this.delete = delete;
    }

    public List<Rule> getAdd() {
        return add;
    }

    public void setAdd(List<Rule> add) {
        this.add = add;
    }

    public RulesDeleteIds getDelete() {
        return delete;
    }

    public void setDelete(RulesDeleteIds delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "AddStreamRulesRequest [add=" + add + ", delete=" + delete + "]";
    }

}
