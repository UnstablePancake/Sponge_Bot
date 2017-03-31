package com.github.UnstablePancake.bot;

import com.github.UnstablePancake.modules.*;
import org.json.JSONObject;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import java.io.FileNotFoundException;

public class SpongeBot {

    public static SpongeBot INSTANCE;
    public static IDiscordClient client;

    public static Moderator moderator;

    public static void main(String[] args){
        try {
            JSONParser.parse("config.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject(JSONParser.jsonData);
        INSTANCE = login(obj.getString("token"));
    }

    public SpongeBot(IDiscordClient client){
        this.client = client;
        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(new EventHandler());
    }

    public static SpongeBot login(String token){
        SpongeBot bot = null;

        ClientBuilder builder = new ClientBuilder();
        builder.withToken(token);
        try {
            IDiscordClient client = builder.login();
            bot = new SpongeBot(client);
        } catch (DiscordException e){
            System.err.println("Error occurred while logging in!");
            e.printStackTrace();
        }
        return bot;
    }

    public class EventHandler {

        @EventSubscriber
        public void onReadyEvent(ReadyEvent event){
            moderator = new Moderator(client, client.getChannels().get(0));
            new CommandHandler(client);
            System.out.println("##########################\n# Spongebot is now ready #\n##########################");
        }

        @EventSubscriber
        public void onMessageReceivedEvent(MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
            if (event.getMessage().getChannel().getID().equals(client.getChannels().get(0)))
                moderator.recordMessage(event);
        }
    }
}
