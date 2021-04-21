package com.example.covidapp;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

    public static String extractString(JSONObject json ,String kind){

        try {
            JSONObject jsonObject = new JSONObject(json.toString());

            return jsonObject.getString("cases");
        } catch (JSONException e) {
            return "Extract failed!";
        }

    }

}
