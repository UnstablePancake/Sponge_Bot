package com.github.UnstablePancake.modules.games.points;

import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.games.trivia.Trivia;
import sx.blah.discord.handle.obj.IUser;
import java.util.ArrayList;

public class Points {

    // remove variables
    private static ArrayList<String> names = UserData.names;
    private static ArrayList<String> ids = UserData.ids;
    private static ArrayList<Integer> points = UserData.points;

    public static void addPoints(IUser user){
        int index = UserData.getIndex(user.getID());
        if(index >= 0){
            UserData.points.set(index, UserData.points.get(index) + Trivia.getPoints());
        }
    }

    private static int getCredits(int index){
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

    public static void addCredits(String id, int amount){
        int index = -1;
        for(int i = 0; i < ids.size(); i++){
            if(ids.get(i).equals(id)){
                index = i;
            }
        }
        if(index != -1){
            int initialCredits = points.get(index);
            points.set(index, initialCredits + amount);
        }
    }

    public static boolean hasEnoughMoney(IUser user, int price){
        String userID = user.getID();
        for(int i = 0; i < UserData.ids.size(); i++){
            if(userID.equals(UserData.ids.get(i))){
                if(UserData.points.get(i) >= price)
                    return true;
            }
        }
        return false;
    }

    public static String getTopPlayer(){
        String top = "Not found.";
        int high = 0;
        for(int i = 0; i < points.size(); i++){
            if(high < points.get(i)){
                high = points.get(i);
                top = names.get(i);
            }
        }
        return top;
    }
}
