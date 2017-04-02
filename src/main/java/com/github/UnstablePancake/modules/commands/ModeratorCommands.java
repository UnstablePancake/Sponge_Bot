package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.bot.SpongeBot;
import com.github.UnstablePancake.modules.Moderator;
import sx.blah.discord.api.IDiscordClient;

public class ModeratorCommands extends Commands {

    private Moderator moderator = SpongeBot.moderator;

    public ModeratorCommands(IDiscordClient client) {
        super(client);
        D4JModule.getListener().setPrefix("!");
        prune();
        setServerName();
        setServerDesc();
        shutdown();
    }

    public void prune(){
        reg.registerCommand(new D4JCommandBuilder("prune")
                .build((args, msg) -> {
                    int length;
                    if(msg.getAuthor().getID().equals("164909448043823104") && args.size() > 0){
                        try {
                            length = Integer.parseInt(args.get(0));
                            moderator.getMessageList().deleteBefore(length);
                        } catch (NumberFormatException e){
                            msg.reply("Usage: !prune (amount)");
                        }
                    } else {
                        msg.reply("Usage: !prune (amount)");
                    }
                }));
    }

    public void setServerName(){
        reg.registerCommand(new D4JCommandBuilder("setservername")
                .build((args, msg) -> {
                    if(msg.getAuthor().getID().equals("164909448043823104") && args.size() > 0) {
                        String name = msg.getContent().substring(14);
                        msg.getGuild().changeName(name);
                        msg.delete();
                    }
                }));
    }

    public void setServerDesc(){
        reg.registerCommand(new D4JCommandBuilder("setserverdesc")
                .build((args, msg) -> {
                    if(args.size() > 0){
                        String topic = msg.getContent().substring(14);
                        msg.getGuild().getChannels().get(0).changeTopic(topic);
                        msg.delete();
                    } else {
                        msg.getGuild().getChannels().get(0).changeTopic(null);
                        msg.delete();
                    }
                }));
    }

    public void shutdown(){
        reg.registerCommand(new D4JCommandBuilder("shutdown")
                .build((args, msg) -> {
                    if(msg.getAuthor().getID().equals("164909448043823104"))
                        msg.getClient().logout();
                }));
    }
}
