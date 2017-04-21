package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;

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
                    String ID = "";
                    for(int i = 0; i < rawID.length(); i++){
                        if(Character.isDigit(rawID.charAt(i)))
                            ID += rawID.charAt(i);
                    }
                    msg.getChannel().sendMessage(msg.getClient().getUserByID(ID).getAvatarURL());
        }));
    }
}
