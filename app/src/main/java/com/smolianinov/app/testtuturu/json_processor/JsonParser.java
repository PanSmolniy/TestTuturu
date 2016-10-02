package com.smolianinov.app.testtuturu.json_processor;


import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.smolianinov.app.testtuturu.exp_list.CustomTreeMap;
import com.smolianinov.app.testtuturu.fragments.StationsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;

public class JsonParser
{
    private JSONArray stationsFrom;
    private JSONArray stationsTo;

    public List<String> listFrom = new ArrayList<>();
    public List<String> listTo = new ArrayList<>();

    private Activity activity;


    public List<String> getListFrom() {
        return listFrom;
    }

    public List<String> getListTo() {
        return listTo;
    }

    public JsonParser(Activity activity) {
        this.activity = activity;
        try {
            JSONArray [] arr = parseJson();
            stationsFrom = arr[0];
            //fillStation(listFrom, stationsFrom);
            stationsTo = arr[1];
            //fillStation(listTo, stationsTo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    /*private void fillStation(final List<String> stations, final JSONArray jsonArray) throws JSONException {

        //stations.add(new Station((JSONObject) jsonArray.get(i)).toString());
        new AsyncTask<Void, Void, Void>() {

            //CustomTreeMap<String, List<JSONObject>> stations;
            @Override
            protected Void doInBackground(Void... unusedParams) {
                // TODO: do your database stuff

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    try {
                        stations.add(new Station((JSONObject) jsonArray.get(i)).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                //fragment

                Toast.makeText(activity, "Finished From length: " + listFrom.size() + "To length: "
                        + listTo.size() + "Frist word: " + listFrom.get(0), Toast.LENGTH_LONG).show();



                super.onPostExecute(aVoid);
            }
        }.execute();

    }*/

    public CustomTreeMap<String, List<JSONObject>> orderData() throws JSONException {

        //JSONArray [] arr = parseJson();

        CustomTreeMap<String, List<JSONObject>> result = new CustomTreeMap<>();

        List<Integer> stationIds = new ArrayList<>();
        List<Integer> cityIds = new ArrayList<>();

        for (int i = 0; i < stationsFrom.length(); i++)
        {
            StringBuilder countryCity = new StringBuilder();

            String key = "";
            List<JSONObject> value = new ArrayList<>();

            JSONObject obj = stationsFrom.getJSONObject(i);
            //listFrom.add(new Station(obj).toString());
            countryCity.append(obj.getString(JsonConstants.COUNTRY_TITLE));
            countryCity.append(", ");
            countryCity.append(obj.getString(JsonConstants.CITY_TITLE));
            cityIds.add(obj.getInt(JsonConstants.CITY_ID));

            JSONArray stations = obj.getJSONArray(JsonConstants.STATIONS);

            for (int j = 0; j < stations.length(); j++)
            {
                JSONObject object = (JSONObject) stations.get(j);
                value.add(object);
                listFrom.add(new Station(object).toString());
                stationIds.add((object).getInt(JsonConstants.STATION_ID));
            }

            key = countryCity.toString();

            result.put(key, value);
        }
        return merge(cityIds, stationIds, result);
    }

    private String loadJSONFromAsset() {
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

    private JSONArray[] parseJson() throws JSONException {

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

       /* Log.d("CitiesFromLength", citiesFromJSONArr.length()+"");
        Log.d("CitiesToLength", citiesToJSONArr.length()+"");
        Log.d("TotalLength", citiesFromJSONArr.length() + citiesToJSONArr.length() + "");

        Set<JSONObject> jsonset = new HashSet<>();

        List<JSONObject> lfrom = new ArrayList<>();
        List<JSONObject> lto = new ArrayList<>();

        for (int i = 0; i < citiesFromJSONArr.length(); i++)
        {
            lfrom.add(citiesFromJSONArr.getJSONObject(i));
        }
        for (int i = 0; i < citiesToJSONArr.length(); i++)
        {
            lto.add(citiesToJSONArr.getJSONObject(i));
        }

        jsonset.addAll(lfrom);
        jsonset.addAll(lto);

        Log.d("SetLength", jsonset.size() + "");*/

        return citiesFromcitiesTo;
    }

    private CustomTreeMap<String, List<JSONObject>> merge
            (List<Integer> cityIds, List<Integer> stationIds, CustomTreeMap<String, List<JSONObject>> map)
            throws JSONException {

        CustomTreeMap<String, List<JSONObject>> result = map;
        for (int i = 0; i < stationsTo.length(); i++) {
            StringBuilder countryCity = new StringBuilder();
            String key = "";
            List<JSONObject> value = new ArrayList<>();
            JSONObject obj = stationsTo.getJSONObject(i);

            countryCity.append(obj.getString(JsonConstants.COUNTRY_TITLE));
            countryCity.append(", ");
            countryCity.append(obj.getString(JsonConstants.CITY_TITLE));
            key = countryCity.toString();

            if (!cityIds.contains(obj.getInt(JsonConstants.CITY_ID))) {


                JSONArray stations = obj.getJSONArray(JsonConstants.STATIONS);

                for (int j = 0; j < stations.length(); j++) {
                    listTo.add(new Station((JSONObject) stations.get(j)).toString());
                    value.add((JSONObject) stations.get(j));
                    stationIds.add(((JSONObject) stations.get(j)).getInt(JsonConstants.STATION_ID));
                }

                result.put(key, value);


            } else {
                value = result.get(key);

                JSONArray stations = obj.getJSONArray(JsonConstants.STATIONS);

                for (int j = 0; j < stations.length(); j++) {
                    int stationId = stations.getJSONObject(j).getInt(JsonConstants.STATION_ID);
                    if (!stationIds.contains(stationId)) {
                        value.add((JSONObject) stations.get(j));
                        listTo.add(new Station((JSONObject) stations.get(j)).toString());
                    }
                }
            }
            result.put(key, value);
        }
        return result;
    }






}

