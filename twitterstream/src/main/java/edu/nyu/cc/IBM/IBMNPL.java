package edu.nyu.cc.IBM;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;

/**
 * Created by chaoqunhuang on 11/20/17.
 */
public class IBMNPL {
    public static Double analysis(String tweet) {
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                "c354dc57-89f3-4b19-9d60-02b98139c15d",
                "ytgXPgDHESEM"
        );

        SentimentOptions sentiment = new SentimentOptions.Builder()
                .build();

        Features features = new Features.Builder()
                .sentiment(sentiment)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(tweet)
                .features(features)
                .build();

        AnalysisResults response = service
                .analyze(parameters)
                .execute();
        System.out.println(response.getSentiment().getDocument().getScore());
        return response.getSentiment().getDocument().getScore();
    }
}
