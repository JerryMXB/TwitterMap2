package edu.nyu.cc;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.nyu.cc.ElasticSearch.ElasticSearch;

import java.util.Map;

/**
 * Created by chaoqunhuang on 11/21/17.
 */
public class LambdaHandler implements RequestHandler<Object, String> {
    public String handleRequest(Object placeHolder, Context context) {
        Map<String, String> map = (Map) placeHolder;
        if ("search".equals(map.get("method"))) {
            if ("".equals(map.get("keyword"))) {
                System.out.println("Fetching all Tweets");
                return ElasticSearch.fetchAllTweets();
            }
            System.out.println("Searching for" + map.get("keyword"));
            return ElasticSearch.searchKeyWord(map.get("keyword"));
        } else if ("location".equals(map.get("method"))) {
            System.out.println("Searching for locations");
            return ElasticSearch.filterTweetWithinDistance(Double.valueOf(map.get("lat")), Double.valueOf(map.get("lon")), 500L);
        } else {
            System.out.println("Fetching all Tweets");
            return ElasticSearch.fetchAllTweets();
        }
    }
}
