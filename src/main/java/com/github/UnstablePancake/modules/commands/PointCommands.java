package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.CommandHandler;
import com.github.UnstablePancake.modules.games.points.Points;
import com.github.UnstablePancake.modules.roles.RolePermissions;
import org.apache.commons.lang3.StringUtils;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import java.awt.*;

public class PointCommands extends Commands {

    public PointCommands(IDiscordClient client) {
        super(client);
        D4JModule.getListener().setPrefix("$");
        getPoints();
        transferMoney();
        addPoints();
        leaderboard();
    }

    private void getPoints() {
        reg.registerCommand(new D4JCommandBuilder("points")
                .build((args, msg) -> {
                    if (args.size() == 0) {
                        IUser user = msg.getAuthor();
                        String name = user.getDisplayName(msg.getGuild());
                        msg.getChannel().sendMessage("**" + name + "** has **" + Points.getCredits(user)
                                + "** points\n :trophy: x " + Points.getTrophies(user.getID()));
                    } else {
                        String id = CommandHandler.parseMention(args);
                        if(CommandHandler.isValidMention(args) && !id.equals("@everyone")){
                            String name = msg.getGuild().getUserByID(id).getDisplayName(msg.getGuild());
                            msg.getChannel().sendMessage("**" + name + "** has **" + Points.getCredits(id)
                                    + "** points\n:trophy: x " + Points.getTrophies(name));
                        }
                    }
                }));
    }

    private void transferMoney() {
        reg.registerCommand(new D4JCommandBuilder("give")
                .build((args, msg) -> {
                    if (args.size() == 0) {
                        msg.reply("Please enter the amount of points you want to transfer");
                        return;
                    }

                    if(args.size() == 2) {
                        String points = args.get(1);
                        String sendID = msg.getAuthor().getID();
                        int sendPointsIndex = UserData.getIndex(sendID);

                        if (StringUtils.isNumeric(points)) {
                            if (UserData.points.get(sendPointsIndex) >= Integer.parseInt(points)) {
                                String receiveID = CommandHandler.parseMention(args);
                                int receivePointsIndex = UserData.getIndex(receiveID);

                                int initialSend = UserData.points.get(sendPointsIndex);
                                int initialReceive = UserData.points.get(receivePointsIndex);

                                UserData.points.set(sendPointsIndex, initialSend - Integer.parseInt(points));
                                UserData.points.set(receivePointsIndex, initialReceive + Integer.parseInt(points));

                                IUser receiver = msg.getGuild().getUserByID(receiveID);
                                msg.getChannel().sendMessage(receiver.mention() + " You have received **" + points + "** points from **"
                                        + msg.getGuild().getUserByID(sendID).getDisplayName(msg.getGuild()) + "**");
                            } else
                                msg.reply("You do not have enough points");
                        } else {
                            msg.reply("Invalid point value.");
                        }
                    }
        }));
    }

    private void addPoints(){
        reg.registerCommand(new D4JCommandBuilder("addpoints")
                .build((args, msg) -> {
                    if(RolePermissions.isAdmin(msg)){
                        String user = CommandHandler.parseMention(args);
                        if(args.size() == 2){
                            String points = args.get(1);
                            if(CommandHandler.isValidMention(args)) {
                                if (StringUtils.isNumeric(points)) {
                                    Points.addCredits(user, Integer.parseInt(points));

                                    msg.getChannel().sendMessage(msg.getGuild().getUserByID(user).mention() + " **" + points
                                            + "** points have been added to your account");
                                }
                            } else if(CommandHandler.isValidMentionEveryone(args)){
                                for(String id : UserData.ids){
                                    Points.addCredits(id, Integer.parseInt(points));
                                }
                                msg.getChannel().sendMessage("@everyone **" + points + " points ** have been added to **everybody's** account");
                            }
                        }
                    }
        }));
    }

    private void leaderboard(){
        reg.registerCommand(new D4JCommandBuilder("lb")
                .build((args, msg) -> {
                    if(UserData.points.size() >= 5) {
                        int max = 5;
                        int request = 5;

                        if(args.size() == 1 && StringUtils.isNumeric(args.get(0)) && Integer.parseInt(args.get(0)) <= UserData.points.size()) {
                            request = Integer.parseInt(args.get(0));
                            max = request;
                        }

                        Points.sortLists();

                        String message = "";
                        int[] points = Points.getPointArray();
                        String[] names = Points.getNameArray();

                        for (int i = 0; i < max; i++) {
                            message += ("**" + (i + 1) + ".**  " + names[i] + " - " + points[i] + "\n");
                        }

                        msg.getChannel().sendMessage(null, new EmbedBuilder()
                                .withColor(new Color(255, 255, 255))
                                .appendField(":chart_with_upwards_trend: Leaderboard: Top " + request, message, false)
                                .build(), false);
                    }
                }));
    }
}
