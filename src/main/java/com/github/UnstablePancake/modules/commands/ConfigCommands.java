package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.roles.RolePermissions;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.Status;

public class ConfigCommands extends Commands {

    public ConfigCommands(IDiscordClient client){
        super(client);
        D4JModule.getListener().setPrefix("!");
        setStatus(client);
    }

    private void setStatus(IDiscordClient client){
        reg.registerCommand(new D4JCommandBuilder("setstatus")
                .build((args, msg) -> {
                    if(RolePermissions.isAdmin(msg)){
                        if(args.size() > 0) {
                            String status = msg.getContent().substring(10);
                            client.changeStatus(Status.game(status));
                        } else
                            client.changeStatus(Status.empty());
                    } else {
                        msg.getChannel().sendMessage(RolePermissions.noPermission());
                    }
        }));
    }
}
