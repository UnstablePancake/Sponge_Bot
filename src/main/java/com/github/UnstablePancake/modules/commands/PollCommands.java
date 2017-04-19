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
        createPollWithOptions();
    }

    public void createPoll(){
        reg.registerCommand(new D4JCommandBuilder("poll")
                .build((args, msg) -> {
                    if(args.size() > 0){
                        msg.delete();
                        Poll.resetVotes();
                        Poll.setTitle(msg.getContent().substring(5));

                        String id = msg.getChannel().sendMessage(null, new EmbedBuilder()
                                .withColor(Color.cyan)
                                .appendField(":ballot_box_with_check: Poll: " + Poll.getTitle(), Poll.createGeneralPoll(), false)
                                .build(), false).getID();

                        Poll.setID(id);
                        msg.getClient().getMessageByID(id).addReaction("<:VoteUp:303664389746196480>");
                        Thread.sleep(150);
                        msg.getClient().getMessageByID(id).addReaction("<:VoteDown:303664478057136138>");
                    } else {
                        msg.reply("Usage: /poll (title)");
                    }
                }));
    }

    public void createPollWithOptions(){
        reg.registerCommand(new D4JCommandBuilder("polloption")
                .build((args, msg) -> {
                    if(Poll.isReady(msg.getContent().substring(11)) && args.size() > 0){
                        msg.delete();
                        Poll.resetVotes();
                        Poll.setTitle(Poll.findTitle(msg.getContent().substring(11)));

                        String id = msg.getChannel().sendMessage(null, new EmbedBuilder()
                                .withColor(Color.cyan)
                                .appendField(":ballot_box_with_check: Poll: " + Poll.getTitle(), Poll.createOptionPoll(msg.getContent().substring(11)), false)
                                .build(), false).getID();

                        Poll.setID(id);
                        for(int i = 0; i < Poll.getCount() - 1; i++){
                            msg.getClient().getMessageByID(id).addReaction(Poll.symbol[i]);
                            Thread.sleep(150);
                        }
                    } else {
                        msg.reply("Usage: /polloption (title;option1;option2;option3)");
                    }
                }));
    }
}