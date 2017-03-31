package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.bot.SpongeBot;
import com.github.UnstablePancake.modules.Commands;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.Status;

public class ConfigCommands extends Commands {

    private IDiscordClient client = SpongeBot.client;

    public ConfigCommands(IDiscordClient client){
        super(client);
        D4JModule.getListener().setPrefix("!");
        setStatus(client);
    }

    public void setStatus(IDiscordClient client){
        reg.registerCommand(new D4JCommandBuilder("setstatus")
                .build((args, msg) -> {
                    String status = "";

                    for (String s : args){
                        status += s + " ";
                    }
                    client.changeStatus(Status.game(status));
                }));
    }
}
