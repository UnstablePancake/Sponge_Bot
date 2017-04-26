package com.github.UnstablePancake.modules.games.gamble;

import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.games.Utility;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class Gamble {

    public static void gamble(IUser user, int amount, int random){
        int index = UserData.getIndex(user);
        int points = UserData.points.get(index);

        if(random == 0){
            UserData.points.set(index, points + (amount * 2));
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
            msg.getChannel().sendMessage(user.mention() + " You found **" + randPoints + "** points.");
        } else if (random == 1) {
            UserData.points.set(index, points + 100000);
            msg.getChannel().sendMessage(user.mention() + " You found **100000** points.");
        } else
            msg.getChannel().sendMessage(user.mention() + "You found nothing.");
    }
}
