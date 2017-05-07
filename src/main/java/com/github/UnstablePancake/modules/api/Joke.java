package com.github.UnstablePancake.modules.api;

import com.github.UnstablePancake.bot.JSONHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Joke {

    public static String getYoMammaJoke(String url){
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        try {
            obj = (JSONObject)parser.parse(JSONHandler.readUrl(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (String)obj.get("joke");
    }

    public static String getChuckNorrisJoke(String url){
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        JSONObject jokeObj;
        try {
            obj = (JSONObject)parser.parse(JSONHandler.readUrl(url));

        } catch (Exception e) {
            e.printStackTrace();
        }
        jokeObj = (JSONObject)obj.get("value");
        return (String)jokeObj.get("joke");
    }
}
