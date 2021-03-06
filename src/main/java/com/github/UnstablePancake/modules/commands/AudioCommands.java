package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.modules.Audio;
import sx.blah.discord.api.IDiscordClient;

public class AudioCommands extends Commands {

    Audio audio;

    public AudioCommands(IDiscordClient client){
        super(client);
        audio = new Audio(client);
        D4JModule.getListener().setPrefix("~");
        join();
        leave();
    }

    private void join(){
        reg.registerCommand(new D4JCommandBuilder("join")
                .build((args, msg) -> {
                    audio.joinChannel(msg.getAuthor().getConnectedVoiceChannels().get(0).getID());
        }));
    }

    private void leave(){
        reg.registerCommand(new D4JCommandBuilder("leave")
                .build((args, msg) -> {
                    audio.leaveChannel();
        }));
    }
}