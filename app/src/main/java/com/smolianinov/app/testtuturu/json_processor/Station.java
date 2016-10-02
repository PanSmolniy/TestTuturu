package com.smolianinov.app.testtuturu.json_processor;

import org.json.JSONException;
import org.json.JSONObject;


public class Station {

    JSONObject obj;
    String name;
    String city;
    String district;
    String region;
    String country;

    public Station(JSONObject obj) {
        this.obj = obj;
        turnToStation();
    }

    private void turnToStation()
    {
        try {
            name = obj.getString(JsonConstants.STATION_NAME);
            city = obj.getString(JsonConstants.CITY_TITLE);
            district = obj.getString(JsonConstants.STATION_DISTRICT);
            region = obj.getString(JsonConstants.STATION_REGION);
            country = obj.getString(JsonConstants.STATION_COUNTRY);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public String toString() {

        return append(name) +
                append(city) +
                append(district) +
                append(region) +
                append(country);
    }

    private String append(String text)
    {
        if (text.length() != 0)
        {
             return text + ", ";
        } else return "";
    }
}
