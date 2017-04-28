package com.github.UnstablePancake.modules.games.points;

import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.games.trivia.Trivia;
import sx.blah.discord.handle.obj.IUser;
import java.util.ArrayList;

public class Points {

    public static void addPoints(IUser user){
        int index = UserData.getIndex(user.getID());
        if(index >= 0){
            UserData.points.set(index, UserData.points.get(index) + Trivia.getPoints());
        }
    }

    private static int getCredits(int index){
        return UserData.points.get(index);
    }

    public static int getCredits(IUser user){
        int credits = 0;
        for(int i = 0; i < UserData.ids.size(); i++){
            if(UserData.ids.get(i).equals(user.getID()))
                credits = getCredits(i);
        }
        return credits;
    }

    public static int getCredits(String id){
        int credits = 0;
        for(int i = 0; i < UserData.ids.size(); i++){
            if(UserData.ids.get(i).equals(id))
                credits = getCredits(i);
        }
        return credits;
    }

    public static int getTrophies(String id){
        int t = 0;
        for(int i = 0; i < UserData.ids.size(); i++){
            if(UserData.ids.get(i).equals(id))
                t = UserData.trophies.get(i);
        }
        return t;
    }

    public static void addCredits(String id, int amount){
        int index = -1;
        for(int i = 0; i < UserData.ids.size(); i++){
            if(UserData.ids.get(i).equals(id)){
                index = i;
            }
        }
        if(index != -1){
            int initialCredits = UserData.points.get(index);
            UserData.points.set(index, initialCredits + amount);
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
        for(int i = 0; i < UserData.points.size(); i++){
            if(high < UserData.points.get(i)){
                high = UserData.points.get(i);
                top = UserData.names.get(i);
            }
        }
        return top;
    }
}
