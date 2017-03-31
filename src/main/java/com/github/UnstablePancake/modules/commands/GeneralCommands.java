package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.Commands;
import sx.blah.discord.api.IDiscordClient;

public class GeneralCommands extends Commands {

    public GeneralCommands(IDiscordClient client){
        super(client);
    }

    @Override
    public void register(){
        ping();
    }

    public void ping(){
        reg.registerCommand(new D4JCommandBuilder("ping")
                .build((args, msg) -> {
                    msg.getChannel().sendMessage("pong!");
                }));
    }
}
