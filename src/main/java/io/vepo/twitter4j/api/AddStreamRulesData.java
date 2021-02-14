package io.vepo.twitter4j.api;

public class AddStreamRulesData {
    private String id;
    private String value;
    private String tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "AddStreamRulesData [id=" + id + ", value=" + value + ", tag=" + tag + "]";
    }

}
