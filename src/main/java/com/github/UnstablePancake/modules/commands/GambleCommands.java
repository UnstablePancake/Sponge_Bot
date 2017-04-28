package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.Utility.Utility;
import com.github.UnstablePancake.modules.games.points.Gamble;
import org.apache.commons.lang3.StringUtils;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import java.awt.*;

public class GambleCommands extends Commands {

    public GambleCommands(IDiscordClient client){
        super(client);
        D4JModule.getListener().setPrefix("$");
        gamble();
        //lootPoints();
    }

    private void gamble(){
        reg.registerCommand(new D4JCommandBuilder("gamble")
                .build(((args, msg) -> {
                    final int RANDOM = 1000;

                    IUser user = msg.getAuthor();
                    int index = UserData.getIndex(user.getID());
                    int points = UserData.points.get(index);

                    if(points == 0){
                        msg.reply("You have no points.");
                        return;
                    }
                    if(args.size() > 0){
                        int random = Utility.random(RANDOM);

                        if (args.get(0).equals("all")) {
                            Gamble.gamble(user, points, random);
                        } else if (args.get(0).equals("half")) {
                            points /= 2;
                            Gamble.gamble(user, points, random);
                        } else if (StringUtils.isNumeric(args.get(0))) {
                            points = Integer.parseInt(args.get(0));
                            Gamble.gamble(user, points, random);
                        } else {
                            msg.getChannel().sendMessage(user.mention() + " You entered an invalid amount of points.");
                        }

                        if(args.get(0).equals("all") || args.get(0).equals("half") || StringUtils.isNumeric(args.get(0))){
                            if (random % 2 == 0) {
                                msg.getChannel().sendMessage(null, new EmbedBuilder()
                                        .withColor(new Color(74, 185, 185))
                                        .appendField(":game_die: You won! | `Ticket #" + random + "` ", "You won **" + (int) (points * Gamble.RATE) + "** points.", false)
                                        .withFooterIcon(user.getAvatarURL())
                                        .withFooterText("Total points: " + UserData.points.get(index))
                                        .build(), false);
                            } else {
                                msg.getChannel().sendMessage(null, new EmbedBuilder()
                                        .withColor(new Color(74, 185, 185))
                                        .appendField(":game_die: You lost | `Ticket #" + random + "` ", "You lost **" + points + "** points.", false)
                                        .withFooterIcon(user.getAvatarURL())
                                        .withFooterText("Total points: " + UserData.points.get(index))
                                        .build(), false);
                            }
                        }
                    } else {
                        msg.getChannel().sendMessage(user.mention() + " You entered an invalid amount of points.");
                    }
        })));
    }

    private void lootPoints(){
        reg.registerCommand(new D4JCommandBuilder("loot")
                .build((args, msg) -> {
                    IUser user = msg.getAuthor();
                    int random = Utility.random(1000);
                    Gamble.loot(msg, user, random);
        }));
    }
}
