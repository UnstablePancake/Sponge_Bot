package com.github.UnstablePancake.modules.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class Quote {

    private String key;

    public Quote(){
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject)parser.parse(new FileReader("apiKeys.json"));
            key = (String)obj.get("mashape_key");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        getData();
    }

    public void getData(){
        String json = null;

        try {
            HttpResponse<String> response = Unirest.get("https://thibaultcha-fortunecow-v1.p.mashape.com/random")
                    .header("X-Mashape-Key", key)
                    .header("Accept", "text/plain")
                    .asString();

            json = response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
