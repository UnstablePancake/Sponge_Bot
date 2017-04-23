package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import co.kaioru.distort.d4j.module.DistortD4JModule;
import com.github.UnstablePancake.modules.CommandHandler;
import com.github.UnstablePancake.modules.Points;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;

public class PointCommands extends Commands {

    public PointCommands(IDiscordClient client){
        super(client);
        DistortD4JModule.getListener().setPrefix("$");
        getPoints();
    }

    private void getPoints(){
        reg.registerCommand(new D4JCommandBuilder("points")
                .build((args, msg) -> {
                    if(args.size() == 0){
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
}
