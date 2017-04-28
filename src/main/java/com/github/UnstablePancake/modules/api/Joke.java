package com.github.UnstablePancake.modules.api;

import com.github.UnstablePancake.bot.JSONHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Joke {

    public static String getJoke(String url , String key){
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        try {
            obj = (JSONObject)parser.parse(JSONHandler.readUrl(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (String)obj.get(key);
    }
}
