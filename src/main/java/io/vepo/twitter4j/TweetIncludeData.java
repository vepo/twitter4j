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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((users == null) ? 0 : users.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TweetIncludeData other = (TweetIncludeData) obj;
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TweetIncludeData [users=" + users + "]";
    }

}
