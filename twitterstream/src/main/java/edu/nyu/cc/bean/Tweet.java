package edu.nyu.cc.bean;

import lombok.Data;
import twitter4j.GeoLocation;
/**
 * Created by chaoqunhuang on 11/20/17.
 */
@Data
public class Tweet {
    private final long id;
    private final String text;
    private final String username;
    private final String date;
    private final double[] location;
    private final double sentimentScore;
    public Tweet(long id, String username, String text, String date, double sentimentScore, GeoLocation location) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.date = date;
        this.sentimentScore = sentimentScore;
        this.location = new double[] {location.getLatitude(), location.getLongitude()};
    }
}
