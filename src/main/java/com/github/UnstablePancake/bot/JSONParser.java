package com.github.UnstablePancake.bot;

import org.json.JSONException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONParser {
    public static String jsonData = "";
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
}
