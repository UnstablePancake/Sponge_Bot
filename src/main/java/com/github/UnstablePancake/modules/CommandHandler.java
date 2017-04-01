package com.github.UnstablePancake.modules;

import com.github.UnstablePancake.modules.commands.*;
import sx.blah.discord.api.IDiscordClient;

public class CommandHandler {

    public CommandHandler(IDiscordClient client){
        new AudioCommands(client);
        new ConfigCommands(client);
        new GeneralCommands(client);
        new LuckCommands(client);
        new ModeratorCommands(client);
        new RoleCommands(client);
    }
}
