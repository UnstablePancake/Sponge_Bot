package com.github.UnstablePancake.bot;

import com.github.UnstablePancake.modules.Utility.Ansi;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import java.net.URL;

public class JSONHandler {

    public static String jsonData = "";

    public static void createJSON() throws IOException {
        FileWriter file = new FileWriter("userData.json");
        JSONArray array = new JSONArray();
        for(int i = 0; i < UserData.ids.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("name", UserData.names.get(i));
            obj.put("id", UserData.ids.get(i));
            obj.put("points", UserData.points.get(i));
            array.add(obj);
        }
        file.write(array.toJSONString());
        file.flush();
        System.out.println(Ansi.color("[Data] File userData.json has been created", Ansi.GREEN));
    }


    public static void parse(String config) throws FileNotFoundException, JSONException {

        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(config));

            while ((line = br.readLine()) != null) {
                jsonData = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
