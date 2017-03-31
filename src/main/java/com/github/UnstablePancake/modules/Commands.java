package com.github.UnstablePancake.modules;

import co.kaioru.distort.d4j.module.DistortD4JModule;
import co.kaioru.retort.CommandRegistry;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;

public abstract class Commands {

    public EmbedBuilder embedBuilder;
    public MessageBuilder messageBuilder;
    public DistortD4JModule D4JModule;
    public CommandRegistry reg;

    public Commands(IDiscordClient client){
        embedBuilder = new EmbedBuilder();
        messageBuilder = new MessageBuilder(client);
        D4JModule = new DistortD4JModule();
        client.getModuleLoader().loadModule(D4JModule);
        reg = D4JModule.getRegistry();
        register();
    }

    public abstract void register();
}