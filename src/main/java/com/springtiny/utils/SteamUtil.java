package com.springtiny.utils;

import java.io.*;

public class SteamUtil {
    public static String getString(InputStream is){
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try{
            String line;
            while ((line = reader.readLine())!=null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
