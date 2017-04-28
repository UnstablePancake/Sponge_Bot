package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.api.Joke;
import sx.blah.discord.api.IDiscordClient;

public class APICommands extends Commands {

    public APICommands(IDiscordClient client) {
        super(client);
        D4JModule.getListener().setPrefix("~");
        getYoMammaJoke();
        //getChuckNorrisJoke();
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
}
