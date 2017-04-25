package com.github.UnstablePancake.modules;

import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.games.trivia.Trivia;
import sx.blah.discord.handle.obj.IUser;
import java.util.ArrayList;

public class Points {

    private static ArrayList<String> ids = UserData.ids;
    private static ArrayList<Integer> points = UserData.points;

    public static void addPoints(IUser user){
        int index = UserData.getIndex(user.getID());
        if(index >= 0){
            UserData.points.set(index, UserData.points.get(index) + Trivia.getPoints());
        }
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
