package edu.nyu.cc;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.nyu.cc.ElasticSearch.ElasticSearch;
import edu.nyu.cc.SQS.TwitterSQS;

import java.util.List;

/**
 * Created by chaoqunhuang on 11/21/17.
 */
public class LambdaHandler implements RequestHandler<Object, String> {
    public String handleRequest(Object placeHolder, Context context) {
        List<String> messages = TwitterSQS.readMessage();
        for (String m : messages) {
            System.out.println("message :" + m);
            ElasticSearch.indexToElasticSearch(m);
        }
        return "SUCCESS";
    }
}
