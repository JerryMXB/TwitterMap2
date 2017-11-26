package edu.nyu.cc.SQS;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

/**
 * Created by chaoqunhuang on 11/20/17.
 */
public class TwitterSQS {
    final static AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    final static String queueUrl = "https://sqs.us-east-1.amazonaws.com/842132808572/twitts";
    public static void sendToSQS(String message) {
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
    }
}
