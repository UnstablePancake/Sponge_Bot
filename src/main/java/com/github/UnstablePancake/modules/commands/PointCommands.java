package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import co.kaioru.distort.d4j.module.DistortD4JModule;
import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.CommandHandler;
import com.github.UnstablePancake.modules.Points;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;

public class PointCommands extends Commands {

    public PointCommands(IDiscordClient client) {
        super(client);
        D4JModule.getListener().setPrefix("$");
        getPoints();
        transferMoney();
        leaderboard();
    }

    private void getPoints() {
        reg.registerCommand(new D4JCommandBuilder("points")
                .build((args, msg) -> {
                    if (args.size() == 0) {
                        IUser user = msg.getAuthor();
                        String name = user.getDisplayName(msg.getGuild());
                        msg.getChannel().sendMessage("**" + name + "** has **" + Points.getCredits(user) + "** points");
                    } else {
                        String id = CommandHandler.parseMention(args);
                        String name = msg.getGuild().getUserByID(id).getDisplayName(msg.getGuild());
                        msg.getChannel().sendMessage("**" + name + "** has **" + Points.getCredits(id) + "** points");
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
                    String points = args.get(1);
                    String sendID = msg.getAuthor().getID();
                    int sendPointsIndex = UserData.getIndex(sendID);

                    if (StringUtils.isNumeric(points)) {
                        if (UserData.points.get(sendPointsIndex) >= Integer.parseInt(points)){
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
        }));
    }

    private void leaderboard() {
        reg.registerCommand(new D4JCommandBuilder("leaderboard")
                .build((args, msg) -> {
                    msg.getChannel().sendMessage("**" + Points.getTopPlayer() + "** has the most points.");
                }));
    }
}
