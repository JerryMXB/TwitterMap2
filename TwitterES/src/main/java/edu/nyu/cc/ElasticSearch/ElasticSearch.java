package edu.nyu.cc.ElasticSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ElasticSearch {
    private static final String ES_URL = "https://search-twittermap-qkhshjtekerke6c7rd244mua3i.us-east-1.es.amazonaws.com/tweet2/tweetmap";

    public static void indexToElasticSearch(String body) {
        postWithBody("", body);
    }

    private static String postWithBody(String postUrl, String body) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(ES_URL+postUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestProperty("Accept-Encoding", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream os = connection.getOutputStream();
            os.write(body.getBytes("UTF-8"));
            os.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String temp;
            while((temp = reader.readLine()) != null) {
                sb.append(temp).append(" ");
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}
