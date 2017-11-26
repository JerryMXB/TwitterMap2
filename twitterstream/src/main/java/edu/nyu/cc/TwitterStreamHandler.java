package edu.nyu.cc;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.nyu.cc.stream.TwitterStream;


/**
 * Created by chaoqunhuang on 11/20/17.
 */
public class TwitterStreamHandler implements RequestHandler<Object, String> {
    public String handleRequest(Object placeHolder, Context context) {
        Thread thread = new Thread(new TwitterStream());
        thread.start();
        try {
            Thread.sleep(2000000);
        } catch (InterruptedException ioe) {
            System.out.println(ioe.getMessage());
        }
        return "SUCCESS";
    }
}
