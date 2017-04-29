package com.github.UnstablePancake.modules.Utility;

import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;

import java.util.List;

public class Info {

    public static String typeCase(String s){
        if(s == null)
            return "None";

        String first = s.substring(0, 1).toUpperCase();

        String last = "";
        if(s.length() > 1)
            last = s.substring(1).toLowerCase();

        return first + last;
    }

    public static String getOnlineStatus(IUser user){
        return typeCase(String.valueOf(user.getPresence()));
    }

    public static String getGame(IUser user){
        String game = user.getStatus().getStatusMessage();

        return typeCase(game);
    }

    public static String getVoiceChannels(IUser user){
        List<IVoiceChannel> channel = user.getConnectedVoiceChannels();

        if(channel.size() > 0)
            return channel.get(0).toString();
        return "None";
    }
}
