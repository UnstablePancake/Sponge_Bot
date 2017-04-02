package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.List;

public class RoleCommands extends Commands {

    public RoleCommands(IDiscordClient client) {
        super(client);
        D4JModule.getListener().setPrefix("!");
        addRole();
        remRole();
    }

    public void addRole(){
        reg.registerCommand(new D4JCommandBuilder("addrole")
                .build((args, msg) -> {
                    if (msg.getAuthor().getID().equals("164909448043823104") && args.size() > 0){
                        String id = args.get(0);
                        String text = args.get(1);
                        IRole role = null;
                        List<IRole> roles = msg.getClient().getRoles();

                        for (int i = 0; i < roles.size(); i++) {
                            if (roles.get(i).getName().equalsIgnoreCase(text))
                                role = roles.get(i);
                        }

                        if (role != null){
                            msg.getGuild().getUserByID(id).addRole(role);

                            msg.getChannel().sendMessage(null, new EmbedBuilder()
                                    .withColor(new Color(78, 185, 110))
                                    .appendField(":hammer_pick: Role added", "**Role:** " + role.toString() + "\n**Target:** " + msg.getGuild().getUserByID(id).getName(), false)
                                    .build(), false);
                            msg.delete();
                        }
                    }
                }));
    }

    public void remRole(){
        reg.registerCommand(new D4JCommandBuilder("remrole")
                .build((args, msg) -> {
                    if (msg.getAuthor().getID().equals("164909448043823104") && args.size() > 0) {
                        String id = args.get(0);
                        String text = args.get(1);
                        IRole role = null;
                        List<IRole> roles = msg.getClient().getRoles();

                        for (int i = 0; i < roles.size(); i++) {
                            if (roles.get(i).getName().equalsIgnoreCase(text))
                                role = roles.get(i);
                        }

                        if (role != null) {
                            msg.getGuild().getUserByID(id).removeRole(role);

                            msg.getChannel().sendMessage(null, new EmbedBuilder()
                                    .withColor(new Color(231, 76, 60))
                                    .appendField(":hammer_pick: Role removed", "**Role:** " + role.toString() + "\n**Target:** " + msg.getGuild().getUserByID(id).getName(), false)
                                    .build(), false);
                            msg.delete();
                        }
                    }
                }));
    }
}
