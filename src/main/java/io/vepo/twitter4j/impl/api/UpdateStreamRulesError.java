package io.vepo.twitter4j.impl.api;

public class UpdateStreamRulesError {
    private String id;
    private String value;
    private String title;
    private String type;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AddStreamRulesError [id=" + id + ", value=" + value + ", title=" + title + ", type=" + type + "]";
    }

}
