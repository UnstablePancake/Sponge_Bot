package com.github.UnstablePancake.bot;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class JSONHandler {

    public static String jsonData = "";

    public static void createJSON(ArrayList<String> names, ArrayList<String> id, ArrayList<Integer> points) throws IOException {
        FileWriter file = new FileWriter("userData.json");
        JSONArray array = new JSONArray();
        for(int i = 0; i < id.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("name", names.get(i));
            obj.put("id", id.get(i));
            obj.put("points", points.get(i));
            array.add(obj);
        }
        file.write(array.toJSONString());
        file.flush();
        System.out.println("userData.json has been updated");
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

    public static void syncData(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("userData.json"));

            JSONObject jsonObject = (JSONObject)obj;
            JSONArray data = (JSONArray)jsonObject.get("Eric");
            System.out.println(data);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
