package com.github.UnstablePancake.modules;

import sx.blah.discord.handle.impl.events.ReactionAddEvent;
import sx.blah.discord.handle.impl.events.ReactionRemoveEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import java.awt.*;

public class Poll {

    public static String[] symbol = {
            "<:VoteUp:303664389746196480>",
            "<:VoteDown:303664478057136138>",
            "<:AndyQueer:231973860994449408>",
            "<:Coldslaw:269284728593448970>",
            "<:Tommy420:269248038113902592>",
            "<:JeffreyWut:230126794123116544>"
    };
    private static int[] votes = {0, 0, 0, 0, 0, 0};
    private static String title = null;
    public static String id = null;
    private static int count = 0;
    private static boolean isDefault = true;
    private static String options = null;

    public static String createGeneralPoll(){
        isDefault = true;
        return "<:VoteUp:303664389746196480> (" + votes[0] + ") Yes <:VoteDown:303664478057136138> (" + votes[1] + ") No";
    }

    public static String createOptionPoll(String s){
        isDefault = false;
        options = s;
        count = 0;
        String msg = "";
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) != ';') {
                if (count > 0)
                    msg += s.charAt(i);
            } else {
                msg += "\n" + symbol[count] + " (" + votes[count] + ") ";
                count++;
            }
        }
        return msg;
    }

    public static void addVotes(ReactionAddEvent event){
        if(isDefault){
            if(id.equals(event.getMessage().getID())){
                if(event.getReaction().getCustomEmoji().getID().equals("303664389746196480")) {
                    votes[0]++;
                } else if (event.getReaction().getCustomEmoji().getID().equals("303664478057136138")) {
                    votes[1]++;
                }
                update(event);
            }
        } else {
            if(id.equals(event.getMessage().getID())){
                if(event.getReaction().getCustomEmoji().getID().equals("303664389746196480")){
                    votes[0]++;
                } else if (event.getReaction().getCustomEmoji().getID().equals("303664478057136138")) {
                    votes[1]++;
                } else if (event.getReaction().getCustomEmoji().getID().equals("231973860994449408")) {
                    votes[2]++;
                } else if (event.getReaction().getCustomEmoji().getID().equals("269284728593448970")) {
                    votes[3]++;
                } else if (event.getReaction().getCustomEmoji().getID().equals("269248038113902592")) {
                    votes[4]++;
                } else if (event.getReaction().getCustomEmoji().getID().equals("230126794123116544")) {
                    votes[5]++;
                }
                update(event);
            }
        }
    }

    public static void removeVotes(ReactionRemoveEvent event){
        if(isDefault){
            if (id.equals(event.getMessage().getID())) {
                if (event.getReaction().getCustomEmoji().getID().equals("303664389746196480")) {
                    votes[0]--;
                } else if (event.getReaction().getCustomEmoji().getID().equals("303664478057136138")) {
                    votes[1]--;
                }
                update(event);
            }
        } else {
            if(id.equals(event.getMessage().getID())){
                if (event.getReaction().getCustomEmoji().getID().equals("303664389746196480")){
                    votes[0]--;
                } else if (event.getReaction().getCustomEmoji().getID().equals("303664478057136138")){
                    votes[1]--;
                } else if (event.getReaction().getCustomEmoji().getID().equals("231973860994449408")){
                    votes[2]--;
                } else if (event.getReaction().getCustomEmoji().getID().equals("269284728593448970")){
                    votes[3]--;
                } else if (event.getReaction().getCustomEmoji().getID().equals("269248038113902592")){
                    votes[4]--;
                } else if (event.getReaction().getCustomEmoji().getID().equals("230126794123116544")){
                    votes[5]--;
                }
                update(event);
            }
        }
    }

    public static void update(ReactionRemoveEvent event){
        if(isDefault){
            try {
                event.getClient().getMessageByID(id).edit(null, new EmbedBuilder()
                        .withColor(Color.cyan)
                        .appendField(":ballot_box_with_check: Poll: " + title, Poll.createGeneralPoll(), false)
                        .build());
            } catch (MissingPermissionsException e){
                e.printStackTrace();
            } catch (RateLimitException e){
                e.printStackTrace();
            } catch (DiscordException e){
                e.printStackTrace();
            }
        } else {
            try {
                event.getClient().getMessageByID(id).edit(null, new EmbedBuilder()
                        .withColor(Color.cyan)
                        .appendField(":ballot_box_with_check: Poll: " + title, Poll.createOptionPoll(options), false)
                        .build());
            } catch (MissingPermissionsException e) {
                e.printStackTrace();
            } catch (RateLimitException e) {
                e.printStackTrace();
            } catch (DiscordException e) {
                e.printStackTrace();
            }
        }
    }

    public static void update(ReactionAddEvent event){
        if(isDefault){
            try {
                event.getClient().getMessageByID(id).edit(null, new EmbedBuilder()
                        .withColor(Color.cyan)
                        .appendField(":ballot_box_with_check: Poll: " + title, Poll.createGeneralPoll(), false)
                        .build());
            } catch (MissingPermissionsException e) {
                e.printStackTrace();
            } catch (RateLimitException e){
                e.printStackTrace();
            } catch (DiscordException e){
                e.printStackTrace();
            }
        } else {
            try {
                event.getClient().getMessageByID(id).edit(null, new EmbedBuilder()
                        .withColor(Color.cyan)
                        .appendField(":ballot_box_with_check: Poll: " + title, Poll.createOptionPoll(options), false)
                        .build());
            } catch (MissingPermissionsException e) {
                e.printStackTrace();
            } catch (RateLimitException e) {
                e.printStackTrace();
            } catch (DiscordException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isReady(String s){
        int count = 0;
        for(int i = 0;i < s.length(); i++){
            if(s.charAt(i) == ';'){
                count++;
            }
        }
        return count > 0 && count <= 6;
    }

    public static String findTitle(String s){
        String title = "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ';')
                break;
            title += s.charAt(i);
        }
        return title;
    }

    public static void resetVotes(){
        for(int i = 0; i < votes.length; i++)
            votes[i] = 0;
    }

    public static void setTitle(String s){
        title = s;
    }

    public static void setID(String s){
        id = s;
    }

    public static int getCount(){
        return count;
    }

    public static String getTitle(){
        return title;
    }
}