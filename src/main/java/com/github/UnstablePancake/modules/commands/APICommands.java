package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.api.Joke;
import com.github.UnstablePancake.modules.api.UrbanDictionary;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.EmbedBuilder;
import java.awt.*;

public class APICommands extends Commands {

    UrbanDictionary ud;

    public APICommands(IDiscordClient client) {
        super(client);
        ud = new UrbanDictionary();
        D4JModule.getListener().setPrefix("~");
        getYoMammaJoke();
        //getChuckNorrisJoke();
        getUrbanDictionaryDefinition();
    }

    private void getYoMammaJoke(){
        reg.registerCommand(new D4JCommandBuilder("yomamma")
                .build((args, msg) -> {
                msg.getChannel().sendMessage("<:HeyItsMeKennyBarber:278008448833159168> " + Joke.getJoke("http://api.yomomma.info/", "joke"));
        }));
    }

    private void getChuckNorrisJoke(){
        reg.registerCommand(new D4JCommandBuilder("norris")
                .build((args, msg) -> {
            //fix
                    msg.getChannel().sendMessage(":cowboy: " + Joke.getJoke("http://api.icndb.com/jokes/random", "joke"));
                }));
    }

    private void getUrbanDictionaryDefinition(){
        reg.registerCommand(new D4JCommandBuilder("ud")
                .build((args, msg) -> {
                    ud.getData(args);
                    msg.getChannel().sendMessage(null, new EmbedBuilder()
                            .withColor(new Color(255, 212, 155))
                            .appendField(":notebook_with_decorative_cover: Urban Dictionary", "**Word:**\n" + ud.getWord()
                                    + "\n\n**Definition:**\n" + ud.getDefinition()
                                    + "\n\n**Link:**\n" + ud.getLink(), false)
                            .build(), false);
        }));
    }
}
