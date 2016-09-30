package com.smolianinov.app.testtuturu.json_processor;


import android.app.Activity;
import android.widget.Toast;

import com.smolianinov.app.testtuturu.exp_list.CustomTreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    public CustomTreeMap<String, List<JSONObject>> orderData(JSONArray [] arr) throws JSONException {

        CustomTreeMap<String, List<JSONObject>> result = new CustomTreeMap<>();

        //Country and city name
        //List<String> keys = new ArrayList<>();

        //List<JSONObject> countryAndCityFrom = new ArrayList<>();
       // List<JSONObject> countryAndCityTo = new ArrayList<>();
        //JSONArray from = arr[0];
        //JSONArray to = arr[1];
        for (int i = 0; i < arr[0].length(); i++)
        {
            StringBuilder countryCity = new StringBuilder();
            JSONObject obj = arr[0].getJSONObject(i);
            countryCity.append(obj.getString(JsonConstants.COUNTRY_TITLE));
            countryCity.append(", ");
            countryCity.append(obj.getString(JsonConstants.CITY_TITLE));
            result.put(countryCity.toString(), null);
        }

        return result;

    }






}

