package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.Poll;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

public class PollCommands extends Commands {

    public PollCommands(IDiscordClient client) {
        super(client);
        D4JModule.getListener().setPrefix("/");
        new Poll();
        createPoll();
    }

    public void createPoll(){
        reg.registerCommand(new D4JCommandBuilder("poll")
                .build((args, msg) -> {
                    if(args.size() > 0) {
                        Poll.resetVotes();
                        Poll.setTitle(msg.getContent().substring(5));

                        String id = msg.getChannel().sendMessage(null, new EmbedBuilder()
                                .withColor(Color.cyan)
                                .appendField(":ballot_box_with_check: Poll: " + Poll.getTitle(), Poll.createGeneralPoll(), false)
                                .build(), false).getID();

                        Poll.setID(id);
                        msg.getClient().getMessageByID(id).addReaction("<:Contortionist:248262846222172161>");
                        msg.delete();
                        msg.getClient().getMessageByID(id).addReaction("<:AnnieLUL:277667639382507521>");
                    } else {
                        msg.reply("Usage: /createpoll (title)");
                    }
                }));
    }
}