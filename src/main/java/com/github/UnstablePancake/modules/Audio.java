package com.github.UnstablePancake.modules;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.MissingPermissionsException;

public class Audio {

    private IDiscordClient client;

    public Audio(IDiscordClient client){
        this.client = client;
    }

    public void joinChannel(String channel) throws MissingPermissionsException {
        client.getVoiceChannelByID(channel).join();
    }

    public void leaveChannel(){
        client.getConnectedVoiceChannels().get(0).leave();
    }
}
