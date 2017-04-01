package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.Commands;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

public class LuckCommands extends Commands {

    public LuckCommands(IDiscordClient client){
        super(client);
        D4JModule.getListener().setPrefix(">");
        coinFlip();
        magicConch();
    }

    public void coinFlip(){
        reg.registerCommand(new D4JCommandBuilder("coinflip")
                .build((args, msg) -> {
                    int random = (int)(Math.random() * 2);
                    if(random == 0)
                        msg.reply("Heads");
                    else
                        msg.reply("Tails");
                }));
    }

    public void magicConch(){
        reg.registerCommand(new D4JCommandBuilder("8ball")
                .build((args, msg) -> {
                    String[] outcome = {
                            "It is certain",
                            "It is decidedly so",
                            "Without a doubt",
                            "Yes definitely",
                            "You may rely on it",
                            "Don't count on it",
                            "My reply is no",
                            "My sources say no",
                            "Outlook not so good",
                            "Very doubtful"
                    };

                    int random = (int)(Math.random() * 10);

                    String question = "";
                    for (String s : args){
                        question += s + " ";
                    }

                    msg.getChannel().sendMessage(null, new EmbedBuilder()
                            .withColor(new Color(78, 185, 110))
                            .appendField(":question: Question", question, false)
                            .appendField(":8ball: 8ball", outcome[random], false)
                            .build(), false);
                }));
    }
}
