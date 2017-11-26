package edu.nyu.cc.stream;

import com.google.gson.Gson;
import edu.nyu.cc.IBM.IBMNPL;
import edu.nyu.cc.SNS.TwitterSNS;
import edu.nyu.cc.SQS.TwitterSQS;
import edu.nyu.cc.bean.Tweet;
import edu.nyu.cc.util.DateConvetor;
import twitter4j.Status;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by chaoqunhuang on 11/20/17.
 */
public class TwitterStream implements Runnable{
    private final twitter4j.TwitterStream stream;
    private final TwitterStatusListener listener;

    public TwitterStream() {
        this.listener = new TwitterStatusListener() {
            @Override
            public void onStatus(Status status) {
                if (status.getGeoLocation() != null) {
                    String date = DateConvetor.convert(status.getCreatedAt());

                    Tweet tweet = new Tweet(status.getId(), status.getUser().getScreenName(),
                            status.getText(), date, IBMNPL.analysis(status.getText()), status.getGeoLocation());
                    System.out.println(tweet.toString());
                    TwitterSQS.sendToSQS(new Gson().toJson(tweet));
                    TwitterSNS.publishMessage("{\"tweetId:\":" + status.getId() + "}");
                }
            }
        };
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setJSONStoreEnabled(true)
                .setOAuthConsumerKey("xRAP5oDesacgUOhQu4L7b6raN")
                .setOAuthConsumerSecret("pJZQ2wxYWb987stf9TPgyaJHI8r7JoCCTqYEUpaSz8yDvAu6V7")
                .setOAuthAccessToken("2802092556-4CTFLexYEfYCj5MYospMixSWxsS2Rv72FTwgDVa")
                .setOAuthAccessTokenSecret("mfq08S8y8MzKPJYIVlKCCeFCU1jpNTj8hOd0Ouu0nxGBd");
        this.stream = new TwitterStreamFactory(cb.build()).getInstance();
        this.stream.addListener(listener);
    }

    @Override
    public void run() {
        stream.sample("en");
    }

}