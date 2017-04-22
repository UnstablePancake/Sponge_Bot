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
        announce();
        talk();
    }

    public void ping(){
        reg.registerCommand(new D4JCommandBuilder("ping")
                .build((args, msg) -> {
                    msg.getChannel().sendMessage("pong!");
                }));
    }

    public void announce(){
        reg.registerCommand(new D4JCommandBuilder("announce")
                .build((args, msg) -> {
                    // Change after merge
                    if(msg.getAuthor().getPermissionsForGuild(msg.getGuild()).contains(Permissions.ADMINISTRATOR)){
                        if(args.size() > 0){
                            String message = msg.getContent().substring(9);
                            String channel = msg.getGuild().getChannels().get(0).getID();
                            msg.getClient().getChannelByID(channel).sendMessage(null, new EmbedBuilder()
                                    .withColor(Color.orange)
                                    .appendField(":loudspeaker: Announcement", message, false)
                                    .build(), false);
                        } else {
                            msg.reply("Usage: .announce (message)");
                        }
                    } else {
                        // Add no permission message;
                    }
                }));
    }

    public void talk(){
        reg.registerCommand(new D4JCommandBuilder("talk")
                .build((args, msg) -> {
                    // Change after merge
                    if(msg.getAuthor().getPermissionsForGuild(msg.getGuild()).contains(Permissions.ADMINISTRATOR)){
                        if(args.size() > 0){
                            String message = msg.getContent().substring(5);
                            String channel = msg.getGuild().getChannels().get(0).getID();
                            msg.getGuild().getChannelByID(channel).sendMessage(message);
                        }
                    }
                }));
    }
}
