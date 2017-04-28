package com.github.UnstablePancake.modules.games.points;

import com.github.UnstablePancake.bot.UserData;
import sx.blah.discord.handle.obj.IUser;

public class Market {

    public static void purchaseTrophy(IUser user){
        String userID = user.getID();
        int index = -1;

        for(int i = 0; i < UserData.ids.size(); i++){
            if(userID.equals(UserData.ids.get(i)))
                index = i;
        }
        int initialTrophies = UserData.trophies.get(index);
        UserData.trophies.set(index, initialTrophies + 1);
    }
}
