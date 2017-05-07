package com.github.UnstablePancake.modules.api;

import com.github.UnstablePancake.bot.JSONHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Newton {

    public static String calculate(String operation, String equation){
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        try {
            obj = (JSONObject)parser.parse(JSONHandler.readUrl("https://newton.now.sh/"
                    + operation + "/" + equation));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(obj);
        return (String)obj.get("result");
    }
}
