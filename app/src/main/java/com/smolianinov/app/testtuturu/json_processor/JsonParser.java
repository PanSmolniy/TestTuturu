package com.smolianinov.app.testtuturu.json_processor;


import android.app.Activity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JsonParser
{

    private Activity activity;

    public JsonParser(Activity activity) {
        this.activity = activity;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = activity.getAssets().open("allStations.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {

            ex.printStackTrace();
            Toast.makeText(activity, "Json file not found", Toast.LENGTH_SHORT).show();
            return null;
        }
        return json;

    }



    public JSONArray[] parseJson() throws JSONException {

        //List<JSONObject> stationsFrom = new ArrayList<>();
        //List<JSONObject> stationsTo = new ArrayList<>();
        JSONArray citiesFromcitiesTo[] = new JSONArray[2];
        JSONArray citiesFromJSONArr;
        JSONArray citiesToJSONArr;

        JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
        citiesFromJSONArr = jsonObject.getJSONArray(JsonConstants.CITIES_FROM);
        citiesToJSONArr = jsonObject.getJSONArray(JsonConstants.CITIES_TO);

        citiesFromcitiesTo[0] = citiesFromJSONArr;
        citiesFromcitiesTo[1] = citiesToJSONArr;

        return citiesFromcitiesTo;
    }






}

