package com.github.UnstablePancake.modules.games.points;

import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.Utility.Utility;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class Gamble {

    public static final double RATE = .7;

    public static void gamble(IUser user, int amount, int random){

        int index = UserData.getIndex(user);
        int points = UserData.points.get(index);

        if(random % 2 == 0){
            int payout = (int)(amount * RATE);

            if((int)(amount * RATE) < 1)
                payout = 1;

            UserData.points.set(index, points + payout);
        } else {
            UserData.points.set(index, points - amount);
        }
    }

    public static void loot(IMessage msg, IUser user, int random) throws RateLimitException, DiscordException, MissingPermissionsException {
        int index = UserData.getIndex(user);
        int points = UserData.points.get(index);
        int randPoints = Utility.random(500);

        if (random > 1) {
            UserData.points.set(index, points + randPoints);
            msg.getChannel().sendMessage(user.mention() + ":moneybag: You found **" + randPoints + "** points.");
        } else if (random == 1) {
            UserData.points.set(index, points + 100000);
            msg.getChannel().sendMessage(user.mention() + ":moneybag: You found **100000** points.");
        } else
            msg.getChannel().sendMessage(user.mention() + ":moneybag: You found nothing.");
    }
}
