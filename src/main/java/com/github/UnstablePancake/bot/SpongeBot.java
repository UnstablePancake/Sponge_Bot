package com.github.UnstablePancake.bot;

import com.github.UnstablePancake.modules.*;
import com.github.UnstablePancake.modules.games.trivia.Trivia;
import org.json.JSONObject;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.*;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import java.io.FileNotFoundException;

public class SpongeBot {

    public static SpongeBot INSTANCE;
    public static IDiscordClient client;

    public static Moderator moderator;
    public RoleManager roleManager;

    public static void main(String[] args){
        try {
            JSONHandler.parse("config.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject(JSONHandler.jsonData);
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
        public void onReadyEvent(ReadyEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
            moderator = new Moderator(client, client.getChannels().get(0));
            roleManager = new RoleManager(client.getGuilds().get(0));
            new CommandHandler(client);
            new UserData(client);
            new Trivia(client);
            Schedule.updateUserData();
            System.out.println("Spongebot is ready.");
        }

        @EventSubscriber
        public void onMessageReceivedEvent(MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
            if (event.getMessage().getChannel().getID().equals(client.getChannels().get(0)))
                moderator.recordMessage(event);
        }

        @EventSubscriber
        public void onUserJoinEvent(UserJoinEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
            event.getClient().getChannels().get(0).sendMessage("Welcome to the server! " + event.getUser().mention());
        }

        @EventSubscriber
        public void onReactionAddEvent(ReactionAddEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
            if(!event.getUser().isBot() && event.getReaction().isCustomEmoji()){
                Poll.addVotes(event);
            }
        }

        @EventSubscriber
        public void onReactionRemoveEvent(ReactionRemoveEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
            if(!event.getUser().isBot() && event.getReaction().isCustomEmoji())
                Poll.removeVotes(event);
        }
    }
}
