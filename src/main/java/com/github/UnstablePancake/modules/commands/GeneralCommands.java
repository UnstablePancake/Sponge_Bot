package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.roles.RolePermissions;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

public class GeneralCommands extends Commands {

    public GeneralCommands(IDiscordClient client){
        super(client);
        ping();
        avatar();
        announce();
        talk();
    }

    private void ping(){
        reg.registerCommand(new D4JCommandBuilder("ping")
                .build((args, msg) -> {
                    msg.getChannel().sendMessage("pong!");
        }));
    }

    private void avatar(){
        reg.registerCommand(new D4JCommandBuilder("avatar")
                .build((args, msg) -> {
                    String rawID = args.get(0);
                    String id = "";
                    for(int i = 0; i < rawID.length(); i++){
                        if(Character.isDigit(rawID.charAt(i)))
                            id += rawID.charAt(i);
                    }
                    msg.getChannel().sendMessage(msg.getClient().getUserByID(id).getAvatarURL());
        }));
    }

    private void announce(){
        reg.registerCommand(new D4JCommandBuilder("announce")
                .build((args, msg) -> {
                    if(RolePermissions.isAdmin(msg)){
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
                    }
        }));
    }

    private void talk(){
        reg.registerCommand(new D4JCommandBuilder("say")
                .build((args, msg) -> {
                    if(RolePermissions.isAdmin(msg)){
                        if(args.size() > 0){
                            String message = msg.getContent().substring(5);
                            String channel = msg.getGuild().getChannels().get(0).getID();
                            msg.getGuild().getChannelByID(channel).sendMessage(message);
                        }
                    }
        }));
    }
}
