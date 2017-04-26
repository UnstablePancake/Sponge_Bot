package com.github.UnstablePancake.bot;

import com.github.UnstablePancake.modules.Utility.Ansi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.NickNameChangeEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserData {

    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> ids = new ArrayList<>();
    public static ArrayList<String> joinDate = new ArrayList<>();
    public static ArrayList<Integer> points = new ArrayList<>();

    public UserData(IDiscordClient client){
        syncData();
        initData(client);
        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(new UpdateHandler());
    }

    private static void syncData(){
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("userData.json"));
        } catch (IOException e) {
            System.out.println(Ansi.color("[Data] Error: The system cannot find the file specified, creating file", Ansi.RED));
        } catch (ParseException e) {
            System.out.println(Ansi.color("[Data] Error: File userData.json is empty.", Ansi.RED));
        }
        if(obj != null){
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                names.add((String)jsonObject.get("name"));
                ids.add((String)jsonObject.get("id"));
                points.add(((Long)jsonObject.get("points")).intValue());
            }
        }
    }

    private void initData(IDiscordClient client){
        ArrayList<IUser> users = (ArrayList<IUser>)client.getUsers();
        IGuild guild = client.getGuilds().get(0);
        for(IUser u : users){
            if(!u.isBot() && !isFound(u)){
                names.add(u.getDisplayName(guild));
                ids.add(u.getID());
                points.add(0);
            } else if(isFound(u)){
                String name = names.get(getIndex(u.getID()));
                if(!name.equals(u.getDisplayName(guild))){
                    names.set(getIndex(u.getID()), u.getDisplayName(guild));
                }
            }

        }
        try {
            JSONHandler.createJSON();
        } catch (IOException e) {
            System.out.println(Ansi.color("[Data] Error: userData.json could not be created.", Ansi.RED));
        }
        System.out.println(Ansi.color("[Data] Data has been updated.", Ansi.GREEN));
    }

    private boolean isFound(IUser user){
        for(String id : ids){
            if(id.equals(user.getID()))
                return true;
        }
        return false;
    }

    public static int getIndex(String id){
        for(int i = 0; i < ids.size(); i++){
            if(ids.get(i).equals(id))
                return i;
        }
        return -1;
    }

    public static int getIndex(IUser user){
        int index = UserData.getIndex(user.getID());
        return index;
    }

    private class UpdateHandler {

        @EventSubscriber
        public void onNickNameChangeEvent(NickNameChangeEvent event){
            IUser user = event.getUser();
            names.set(getIndex(user.getID()), user.getDisplayName(event.getGuild()));
        }
    }
}
