package io.vepo.twitter4j.api;

public class Meta {
    private String sent;
    private Summary summary;

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Meta [sent=" + sent + ", summary=" + summary + "]";
    }

}
