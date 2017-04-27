package com.github.UnstablePancake.modules;

import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.commands.*;
import sx.blah.discord.api.IDiscordClient;

import java.util.LinkedList;

public class CommandHandler {

    public CommandHandler(IDiscordClient client){
        new APICommands(client);
        new AudioCommands(client);
        new ConfigCommands(client);
        new GambleCommands(client);
        new GeneralCommands(client);
        new LuckCommands(client);
        new ModeratorCommands(client);
        new PointCommands(client);
        new PollCommands(client);
        new RoleCommands(client);
        new TriviaCommands(client);
    }

    public static String parseMention(LinkedList<String> args){
        String id = args.get(0);
        if (id.startsWith("<@")){
            id = "";
            for (int i = 0; i < args.get(0).length(); i++) {
                if (Character.isDigit(args.get(0).charAt(i)))
                    id += args.get(0).charAt(i);
            }
        } else if(id.equals("@everyone"))
            id = "@everyone";
        return id;
    }

    public static boolean isValidMention(LinkedList<String> args){
        String mention = args.get(0);
        if(mention.startsWith("<@")){
            parseMention(args);
            for(String id : UserData.ids){
                if(id.equals(mention))
                    return true;
            }
            return false;
        } else if(mention.equals("@everyone"))
            return false;
        else if(!mention.startsWith("<@"))
            return false;
        return true;
    }

    public static boolean isValidMentionEveryone(LinkedList<String> args){
        String mention = args.get(0);
        if(mention.startsWith("<@")){
            parseMention(args);
            for(String id : UserData.ids){
                if(id.equals(mention))
                    return true;
            }
        }
        return false;
    }
}
