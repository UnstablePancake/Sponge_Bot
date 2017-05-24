package com.github.UnstablePancake.modules.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class UrbanDictionary {

    private String key;
    private String word;
    private String definition;
    private String link;

    public UrbanDictionary(){
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject)parser.parse(new FileReader("apiKeys.json"));
            key = (String)obj.get("mashape_key");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void getData(LinkedList<String> args){
        String search = StringUtils.join(args, "+");
        String json = null;

        try {
            HttpResponse<String> response = Unirest.get("https://mashape-community-urban-dictionary.p.mashape.com/define?term=" + search)
                    .header("X-Mashape-Key", key)
                    .header("Accept", "text/plain")
                    .asString();

            json = response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject)parser.parse(json);
            JSONArray list = (JSONArray)jsonObject.get("list");
            JSONObject obj = (JSONObject)list.get(0);
            word = (String)obj.get("word");
            definition = (String)obj.get("definition");
            link = (String)obj.get("permalink");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getWord(){
        return word;
    }

    public String getDefinition(){
        return definition;
    }

    public String getLink(){
        return link;
    }
}
