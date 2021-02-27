package com.example.covidapp;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdaterForTextView implements Updater {

    private TextView target;
    private Context context;

    public UpdaterForTextView(TextView target, Context context){
        this.target = target;
        this.context = context;
    }

    public void fetchAndUpdateText(String url, String kindOfData){

        Fetch.fetch(this.context, this, url);

    }

    public void changeTarget(TextView newTarget){
        this.target = newTarget;
    }

    public void changeTargetText(String text){
        this.target.setText(text);
    }

    //Implementation of Interface's toResponse
    public void toResponse(String response) {

        target.setVisibility(View.VISIBLE);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String finalText = JSONParser.extractString(jsonObject ,"cases");

        this.changeTargetText(finalText);

    }
}
