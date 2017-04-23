package com.github.UnstablePancake.modules;

import com.github.UnstablePancake.bot.JSONHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sx.blah.discord.handle.obj.IUser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Points {

    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<String> ids = new ArrayList<>();
    private static ArrayList<Integer> points = new ArrayList<>();

    public Points(){
        syncData();
    }

    private static void syncData(){
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("userData.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("File userData.json is empty");
        }
        if(obj != null){
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                names.add((String) jsonObject.get("name"));
                ids.add((String) jsonObject.get("id"));
                points.add(((Long) jsonObject.get("points")).intValue());
            }
        }
    }

    public static void addID(IUser user){
        String id = user.getID();
        ids.add(id);
    }

    public static void removeID(IUser user){
        for(int i = 0; i < ids.size(); i++){
            if(ids.get(i).equals(user.getID()))
                ids.remove(i);
        }
    }

    public static void addCredits(int index, int num){
        Integer temp = points.get(index) + num;
        points.set(index, temp);


    }

    public static void removeCredits(int index, int num){
        Integer temp = points.get(index) - num;
        points.set(index, temp);
        // unwrite credits
    }

    public static int getCredits(int index){
        return points.get(index);
    }

    public static int getCredits(IUser user){
        int credits = 0;
        for(int i = 0; i < ids.size(); i++){
            if(ids.get(i).equals(user.getID()))
                credits = getCredits(i);
        }
        return credits;
    }

    public static int getCredits(String id){
        int credits = 0;
        for(int i = 0; i < ids.size(); i++){
            if(ids.get(i).equals(id))
                credits = getCredits(i);
        }
        return credits;
    }
}
