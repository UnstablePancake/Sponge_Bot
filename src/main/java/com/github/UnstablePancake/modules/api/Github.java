package com.github.UnstablePancake.modules.api;

import com.github.UnstablePancake.bot.JSONHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Github {

    public static JSONObject getUser(String user){
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        try {
            obj = (JSONObject)parser.parse(JSONHandler.readUrl("https://api.github.com/users/" + user));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String getLogin(JSONObject object){
        return (String)object.get("login");
    }

    public static String getAvatarUrl(JSONObject object){
        return (String)object.get("avatar_url");
    }

    public static String getURL(JSONObject object){
        return (String)object.get("url");
    }

    public static String getName(JSONObject object){
        return (String)object.get("name");
    }

    public static String getBlog(JSONObject object){
        return (String)object.get("blog");
    }

    public static String getRepos(JSONObject object){
        return String.valueOf(object.get("public_repos"));
    }

    public static String getGists(JSONObject object){
        return String.valueOf(object.get("public_gists"));
    }
}
