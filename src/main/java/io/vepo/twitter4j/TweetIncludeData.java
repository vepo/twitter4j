package io.vepo.twitter4j;

import java.util.List;

public class TweetIncludeData {
    private List<TweetUserData> users;

    public List<TweetUserData> getUsers() {
        return users;
    }

    public void setUsers(List<TweetUserData> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "TweetIncludeData [users=" + users + "]";
    }

}
