package com.github.UnstablePancake.modules;

import sx.blah.discord.handle.impl.events.ReactionAddEvent;
import sx.blah.discord.handle.impl.events.ReactionRemoveEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.awt.*;
import java.util.LinkedList;

public class Poll {

    public static String[] symbol = {":thumbsup:", ":thumbsdown:", ":punch:", ":fist:", ":v:", ":ok_hand:"};
    private static int[] votes = {0, 0, 0, 0, 0, 0};
    private static String title = null;
    public static String id = null;

    public static String createOptionPoll(LinkedList<String> list){
        String poll = "";
        for(int i = 1; i < list.size(); i++){
            if(i > symbol.length)
                break;
            else {
                poll += symbol[i] + " " + list.get(i) + "\n";
            }
        }
        return poll;
    }

    public static String createGeneralPoll(){
        return "<:VoteUp:303664389746196480> (" + votes[0] + ") Yes <:VoteDown:303664478057136138> (" + votes[1] + ") No";
    }

    public static void addVotes(ReactionAddEvent event){
        if(id.equals(event.getMessage().getID())){
            if(event.getReaction().getCustomEmoji().getID().equals("303664389746196480")){
                votes[0]++;
            }
            else if(event.getReaction().getCustomEmoji().getID().equals("303664478057136138")){
                votes[1]++;
            }
            update(event);
        }
    }

    public static void removeVotes(ReactionRemoveEvent event){
        if(id.equals(event.getMessage().getID())){
            if(event.getReaction().getCustomEmoji().getID().equals("303664389746196480")){
                votes[0]--;
            }
            else if(event.getReaction().getCustomEmoji().getID().equals("303664478057136138")){
                votes[1]--;
            }
            update(event);
        }
    }

    public static void update(ReactionAddEvent event){
        try {
            event.getClient().getMessageByID(id).edit(null, new EmbedBuilder()
                    .withColor(Color.cyan)
                    .appendField(":ballot_box_with_check: Poll: " + title, Poll.createGeneralPoll(), false)
                    .build());
        } catch (MissingPermissionsException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        } catch (DiscordException e) {
            e.printStackTrace();
        }
    }

    public static void update(ReactionRemoveEvent event){
        try {
            event.getClient().getMessageByID(id).edit(null, new EmbedBuilder()
                    .withColor(Color.cyan)
                    .appendField(":ballot_box_with_check: Poll: " + title, Poll.createGeneralPoll(), false)
                    .build());
        } catch (MissingPermissionsException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        } catch (DiscordException e) {
            e.printStackTrace();
        }
    }

    public static void setTitle(String s){
        title = s;
    }

    public static void setID(String s){
        id = s;
    }

    public static String getTitle(){
        return title;
    }

    public static void resetVotes(){
        for(int i = 0; i < votes.length; i++)
            votes[i] = 0;
    }
}