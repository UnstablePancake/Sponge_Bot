package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.games.trivia.Trivia;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

public class TriviaCommands extends Commands {

    public TriviaCommands(IDiscordClient client){
        super(client);
        D4JModule.getListener().setPrefix("?");
        trivia();
        answer();
    }

    private void trivia(){
        reg.registerCommand(new D4JCommandBuilder("t")
                .build((args, msg) -> {
                if(!Trivia.inProgress) {
                    Trivia.createTrivia();
                }
                msg.getChannel().sendMessage(null, new EmbedBuilder()
                        .withColor(new Color(255, 0, 255))
                        .appendField(":question: Trivia", "**Question**\n" + Trivia.getQuestion() + "\n\n**Hint**\n" + Trivia.getHint(), false)
                        .build(), false);
        }));
    }

    private void answer(){
        reg.registerCommand(new D4JCommandBuilder("a")
                .build((args, msg) -> {
                    if(Trivia.getAnswer() != null) {
                        msg.getChannel().sendMessage(Trivia.getAnswer());
                        Trivia.createTrivia();
                    } else {
                        msg.getChannel().sendMessage("No game in progress");
                    }
                }));
    }
}
