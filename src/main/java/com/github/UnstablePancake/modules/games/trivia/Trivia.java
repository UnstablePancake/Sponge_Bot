package com.github.UnstablePancake.modules.games.trivia;

import com.github.UnstablePancake.bot.JSONHandler;
import com.github.UnstablePancake.modules.games.points.Points;
import com.github.UnstablePancake.modules.Utility.Utility;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class Trivia {

    private static JSONArray list;
    private static String question;
    private static String answer;
    private static String hint;
    private static final int POINTS = 100;
    public static boolean inProgress = false;

    public Trivia(IDiscordClient client){
        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(new EventHandler());
    }

    public static void createTrivia() {
        inProgress = true;
        String json = null;
        do {
            try {
                json = JSONHandler.readUrl("http://jservice.io/api/random");
            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONParser parser = new JSONParser();

            try {
                list = (JSONArray) parser.parse(json);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int random = Utility.random(list.size());
            JSONObject obj = (JSONObject) list.get(random);
            question = (String) obj.get("question");
            answer = fixAnswer((String)obj.get("answer"));
        } while (isInvalidAnswer(answer));

        hint = createHint();
    }

    private static boolean isInvalidAnswer(String a){
        CharSequence[] chars = {"<", "(", "\'", "\"", "\\"};
        for(CharSequence c : chars){
            if(a.contains(c))
                return true;
        }
        return false;
    }

    private static String fixAnswer(String s){
        if(s.startsWith("<")){
            s = s.substring(3, s.length() - 4);
        }
        if(s.contains("(") || s.contains(")")) {
            String temp = "";
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) != '(' && s.charAt(i) != ')')
                    temp += s.charAt(i);
            }
            s = temp;
        }
        return s;
    }

    private static String createHint(){
        String hint = "";
        for(int i = 0; i < answer.length(); i++){
            int random = Utility.random(2);
            char letter = answer.charAt(i);
            if(StringUtils.isAlphaSpace(String.valueOf(letter))){
                if (random == 0) {
                    hint += letter;
                } else if (letter != ' ')
                    hint += " \\_ ";
                if (letter == ' ')
                    hint += "    ";
            } else {
                hint += letter;
            }
        }
        return hint;
    }

    private static boolean isCorrect(String guess){
        return answer.equalsIgnoreCase(guess);
    }

    public static String getQuestion(){
        return question;
    }

    public static String getAnswer(){
        return answer;
    }

    public static String getHint(){
        return hint;
    }

    public static int getPoints(){
        return POINTS;
    }

    private class EventHandler {

        @EventSubscriber
        public void onMessageRecievedEvent(MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
            if(answer != null) {
                IMessage msg = event.getMessage();
                if (isCorrect(msg.getContent())){
                    inProgress = false;
                    createTrivia();
                    Points.addPoints(msg.getAuthor());
                    msg.getChannel().sendMessage("**" + msg.getAuthor().getDisplayName(msg.getGuild()) + "**" + " has earned **" + POINTS + "** points");
                }
            }
        }
    }
}
