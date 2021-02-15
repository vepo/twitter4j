package io.vepo.twitter4j.stream;

import java.util.List;
import java.util.Objects;

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
        result = prime * result + Objects.hashCode(media);
        result = prime * result + Objects.hashCode(tweets);
        result = prime * result + Objects.hashCode(users);
        result = prime * result + Objects.hashCode(places);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TweetInfoIncludes other = (TweetInfoIncludes) obj;
        return Objects.equals(media, other.media) &&
                Objects.equals(tweets, other.tweets) &&
                Objects.equals(users, other.users) &&
                Objects.equals(places, other.places);
    }

    @Override
    public String toString() {
        return String.format("TweetInfoIncludes [users=%s, tweets=%s, media=%s, places=%s]", users, tweets, media,
                             places);
    }

}
