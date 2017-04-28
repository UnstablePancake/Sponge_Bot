package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.bot.JSONHandler;
import com.github.UnstablePancake.bot.Schedule;
import com.github.UnstablePancake.bot.SpongeBot;
import com.github.UnstablePancake.modules.Moderator;
import com.github.UnstablePancake.modules.roles.RolePermissions;
import sx.blah.discord.api.IDiscordClient;

public class ModeratorCommands extends Commands {

    private Moderator moderator = SpongeBot.moderator;

    public ModeratorCommands(IDiscordClient client){
        super(client);
        D4JModule.getListener().setPrefix("!");
        prune();
        setServerName();
        setServerDesc();
        syncUserData();
        shutdown();
    }

    private void prune(){
        reg.registerCommand(new D4JCommandBuilder("prune")
                .build((args, msg) -> {
                    int length;
                    if(RolePermissions.isAdmin(msg)){
                        if(args.size() > 0){
                            try {
                                length = Integer.parseInt(args.get(0));
                                moderator.getMessageList().deleteBefore(length);
                            } catch (NumberFormatException e) {
                                msg.reply("Usage: !prune (amount)");
                            }
                        } else {
                            msg.reply("Usage: !prune (amount)");
                        }
                    } else {
                        msg.getChannel().sendMessage(RolePermissions.noPermission());
                    }
        }));
    }

    private void setServerName(){
        reg.registerCommand(new D4JCommandBuilder("setname")
                .build((args, msg) -> {
                    if(args.size() > 0) {
                        String name = msg.getContent().substring(8);
                        msg.getGuild().changeName(name);
                        msg.delete();
                    }
        }));
    }

    private void setServerDesc(){
        reg.registerCommand(new D4JCommandBuilder("setdesc")
                .build((args, msg) -> {
                    if(args.size() > 0){
                        String topic = msg.getContent().substring(8);
                        msg.getGuild().getChannels().get(0).changeTopic(topic);
                        msg.delete();
                    } else {
                        msg.getGuild().getChannels().get(0).changeTopic(null);
                        msg.delete();
                    }
        }));
    }

    private void syncUserData(){
        reg.registerCommand(new D4JCommandBuilder("sync")
                .build((args, msg) -> {
                    if(RolePermissions.isAdmin(msg))
                        JSONHandler.createJSON();
        }));
    }

    private void shutdown(){
        reg.registerCommand(new D4JCommandBuilder("shutdown")
                .build((args, msg) -> {
                    if(RolePermissions.isAdmin(msg)){
                        JSONHandler.createJSON();
                        msg.getChannel().sendMessage("Shutting down...");
                        Schedule.scheduler.shutdownNow();
                        msg.getClient().logout();
                    }
        }));
    }
}
