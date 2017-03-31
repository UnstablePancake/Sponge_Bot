package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.Commands;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.json.objects.EmbedObject;

import java.awt.*;

public class LuckCommands extends Commands {

    public LuckCommands(IDiscordClient client){
        super(client);
        D4JModule.getListener().setPrefix(">");
    }

    @Override
    public void register(){
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

                    EmbedObject object = embedBuilder.withColor(Color.red).withDesc(":8ball: 8ball\n" + outcome[random]).build();
                    messageBuilder.withEmbed(object).withChannel(msg.getChannel()).send();
                }));
    }
}
