package com.github.UnstablePancake.modules.games.points;

import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.games.trivia.Trivia;
import sx.blah.discord.handle.obj.IUser;

public class Points {

    private static int[] pointArray;
    private static String[] nameArray;

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

    public static void sortLists(){
        pointArray = new int[UserData.points.size()];
        nameArray = new String[UserData.points.size()];

        for(int i = 0; i < UserData.points.size(); i++){
            pointArray[i] = UserData.points.get(i);
            nameArray[i] = UserData.names.get(i);
        }

        for (int i = 0; i < pointArray.length - 1; i++) {
            int j = i + 1;
            int tmp = pointArray[j];
            String tmp2 = nameArray[j];
            while (j > 0 && tmp > pointArray[j-1]) {
                pointArray[j] = pointArray[j-1];
                nameArray[j] = nameArray[j-1];
                j--;
            }
            pointArray[j] = tmp;
            nameArray[j] = tmp2;
        }
    }

    public static int[] getPointArray(){
        return pointArray;
    }

    public static String[] getNameArray(){
        return nameArray;
    }
}
