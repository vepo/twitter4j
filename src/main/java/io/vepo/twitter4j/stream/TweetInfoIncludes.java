package io.vepo.twitter4j.stream;

import java.util.List;

public class TweetInfoIncludes {
    private List<TweetInfoIncludesUserData> users;
    private List<TweetInfoData> tweets;
    private List<TweetInfoMediaData> media;
    private List<TweetInfoPlaceData> places;

    public List<TweetInfoIncludesUserData> getUsers() {
        return users;
    }

    public void setUsers(List<TweetInfoIncludesUserData> users) {
        this.users = users;
    }

    public List<TweetInfoData> getTweets() {
        return tweets;
    }

    public void setTweets(List<TweetInfoData> tweets) {
        this.tweets = tweets;
    }

    public List<TweetInfoMediaData> getMedia() {
        return media;
    }

    public void setMedia(List<TweetInfoMediaData> media) {
        this.media = media;
    }

    public List<TweetInfoPlaceData> getPlaces() {
        return places;
    }

    public void setPlaces(List<TweetInfoPlaceData> places) {
        this.places = places;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((media == null) ? 0 : media.hashCode());
        result = prime * result + ((tweets == null) ? 0 : tweets.hashCode());
        result = prime * result + ((users == null) ? 0 : users.hashCode());
        result = prime * result + ((places == null) ? 0 : places.hashCode());
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
        TweetInfoIncludes other = (TweetInfoIncludes) obj;
        if (media == null) {
            if (other.media != null)
                return false;
        } else if (!media.equals(other.media))
            return false;
        if (tweets == null) {
            if (other.tweets != null)
                return false;
        } else if (!tweets.equals(other.tweets))
            return false;
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        if (places == null) {
            if (other.places != null)
                return false;
        } else if (!places.equals(other.places))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TweetInfoIncludes [users=" + users + ", tweets=" + tweets + ", media=" + media + ", places=" + places
                + "]";
    }

}
