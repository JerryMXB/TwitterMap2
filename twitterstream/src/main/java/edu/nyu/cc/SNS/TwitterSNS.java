package edu.nyu.cc.SNS;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

/**
 * Created by chaoqunhuang on 11/21/17.
 */
public class TwitterSNS {
    final static AmazonSNS sns = AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    final static String topicUrl = "arn:aws:sns:us-east-1:842132808572:TwitterMap";

    public static void publishMessage(String message) {
        PublishRequest publishRequest = new PublishRequest(topicUrl, message);
        PublishResult publishResult = sns.publish(publishRequest);
        //print MessageId of message published to SNS topic
        System.out.println("Publish MessageId - " + publishResult.getMessageId());
    }
}
