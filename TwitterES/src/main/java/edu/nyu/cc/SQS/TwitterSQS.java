package edu.nyu.cc.SQS;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chaoqunhuang on 11/21/17.
 */
public class TwitterSQS {
    final static AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    final static String queueUrl = "https://sqs.us-east-1.amazonaws.com/842132808572/twitts";
    public static List<String> readMessage() {
        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        List<String> jsonMessage = new ArrayList<>();
        for (Message message : messages) {
            jsonMessage.add(message.getBody());
            sqs.deleteMessage(queueUrl, message.getReceiptHandle());
        }
        return jsonMessage;
    }
}
