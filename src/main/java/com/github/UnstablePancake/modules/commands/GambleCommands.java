package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import sx.blah.discord.api.IDiscordClient;

public class GambleCommands extends Commands {

    public GambleCommands(IDiscordClient client){
        super(client);
    }

    private void points(){
        reg.registerCommand(new D4JCommandBuilder("points")
                .build((args, msg) -> {

        }));
    }
}
