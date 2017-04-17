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
                    if(args.size() > 0){
                        Poll.resetVotes();
                        Poll.setTitle(msg.getContent().substring(5));

                        String id = msg.getChannel().sendMessage(null, new EmbedBuilder()
                                .withColor(Color.cyan)
                                .appendField(":ballot_box_with_check: Poll: " + Poll.getTitle(), Poll.createGeneralPoll(), false)
                                .build(), false).getID();

                        Poll.setID(id);
                        msg.getClient().getMessageByID(id).addReaction("<:VoteUp:303664389746196480>");
                        msg.delete();
                        msg.getClient().getMessageByID(id).addReaction("<:VoteDown:303664478057136138>");
                    } else {
                        msg.reply("Usage: /createpoll (title)");
                    }
                }));
    }

    public void createPollWithOptions(){
        reg.registerCommand(new D4JCommandBuilder("polloption")
                .build((args, msg) -> {
                    if(args.size() > 0){
                        Poll.resetVotes();
                        Poll.setTitle("#TEST#");

                        String id = msg.getChannel().sendMessage(null, new EmbedBuilder().withColor(Color.cyan).appendField(":ballot_box_with_check: Poll: " + Poll.getTitle(), Poll.createOptionPoll(args), false)
                        .build(), false).getID();

                        Poll.setID(id);
                        msg.getClient().getMessageByID(id).addReaction("");
                        msg.delete();
                        msg.getClient().getMessageByID(id).addReaction("");
                    }
                }));
    }
}