package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

public class GeneralCommands extends Commands {

    public GeneralCommands(IDiscordClient client){
        super(client);
        ping();
        avatar();
    }

    public void ping(){
        reg.registerCommand(new D4JCommandBuilder("ping")
                .build((args, msg) -> {
                    msg.getChannel().sendMessage("pong!");
                }));
    }

    public void avatar(){
        reg.registerCommand(new D4JCommandBuilder("avatar")
                .build((args, msg) -> {
                    String rawID = args.get(0);
                    String id = "";
                    for(int i = 0; i < rawID.length(); i++){
                        if(Character.isDigit(rawID.charAt(i)))
                            id += rawID.charAt(i);
                    }
                    msg.getChannel().sendMessage(msg.getClient().getUserByID(id).getAvatarURL());
        }));
    }
}
