package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.CommandHandler;
import com.github.UnstablePancake.modules.Utility.Info;
import com.github.UnstablePancake.modules.roles.RolePermissions;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import java.awt.*;

public class GeneralCommands extends Commands {

    public GeneralCommands(IDiscordClient client){
        super(client);
        ping();
        avatar();
        announce();
        talk();
        userInfo();
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

    private void userInfo(){
        reg.registerCommand(new D4JCommandBuilder("info")
                .build((args, msg) -> {
                    IUser user = msg.getAuthor();

                    if(args.size() > 0)
                        user = msg.getGuild().getUserByID(CommandHandler.parseMention(args));

                    Color roleColor = user.getRolesForGuild(msg.getGuild()).get(0).getColor();

                    msg.getChannel().sendMessage(null, new EmbedBuilder()
                            .withColor(roleColor)
                            .ignoreNullEmptyFields()
                            .withAuthorIcon(user.getAvatarURL())
                            .withThumbnail(user.getAvatarURL())
                            .appendField(":clipboard: User info", ":bust_in_silhouette: **User:**\n" + user.getName() + "#" + user.getDiscriminator(), false)
                            .appendField(":busts_in_silhouette: **Nickname:** ", user.getDisplayName(msg.getGuild()), true)
                            .appendField(":card_box: **User ID:** ", user.getID(), true)
                            .appendField(":calendar_spiral: **Account Created:** ", msg.getAuthor().getCreationDate().toString().substring(0, 10), true)
                            .appendField(":date: **Server Join Date:** ", msg.getGuild().getJoinTimeForUser(user).toString().substring(0, 10), true)
                            .appendField(":sound: **Voice Channel:** ", Info.getVoiceChannels(user), false)
                            .appendField(":satellite_orbital: **Status:** ", Info.getOnlineStatus(user), true)
                            .appendField(":video_game: **Game:** ", Info.getGame(user), true)
                            .build(), false);
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
