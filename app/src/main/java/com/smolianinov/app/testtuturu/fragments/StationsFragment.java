package com.smolianinov.app.testtuturu.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;


import com.smolianinov.app.testtuturu.exp_list.CustomTreeMap;
import com.smolianinov.app.testtuturu.exp_list.ExpListAdapter;
import com.smolianinov.app.testtuturu.R;
import com.smolianinov.app.testtuturu.json_processor.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class StationsFragment extends Fragment {

    private JsonParser parser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stations_chooser_fragment, container, false);
        //initializeListView(rootView, returnStations());


        parser = new JsonParser(getActivity());
        processJsonData(rootView);


        return rootView;
    }

    /*private CustomTreeMap<String, List<Object>> returnStations(CustomTreeMap<String, List<Object>> stations)
    {

        CustomTreeMap<String, List<Obj
        stations.put("Albania", null);
        stations.put("Armenia", null);
        stations.put("Cameroon", null);
        stations.put("Ukraine", null);
        stations.put("Azerbaijan", null);
        stations.put("USA", null);
        stations.put("Russia", null);
        stations.put("Belarus", null);
        return stations;
    }*/

    private void initializeListView(View view, CustomTreeMap<String, List<JSONObject>> stations)
    {
        ExpandableListView listView = (ExpandableListView)view.findViewById(R.id.expandableListView);

        ExpListAdapter adapter = new ExpListAdapter(getActivity().getApplicationContext(), stations);
        listView.setAdapter(adapter);
    }

    public static StationsFragment newInstance() {
        return new StationsFragment();
    }


    private void processJsonData(final View v)
    {

        new AsyncTask<Void, Void, Void>() {


            //JSONArray[] arr;
            CustomTreeMap<String, List<JSONObject>> stations;
            @Override
            protected Void doInBackground(Void... unusedParams) {
                // TODO: do your database stuff



                try {
                    //arr = parser.parseJson();
                    stations = parser.orderData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                initializeListView(v, stations);

                /*Toast.makeText(getActivity(), "CitiesFrom length is " + arr[0].length() + " " +
                        "CitiesTo length is " + arr[1].length(), Toast.LENGTH_LONG).show();*/
                super.onPostExecute(aVoid);
            }
        }.execute();


       /* try {
            JSONArray[] arr = parser.parseJson();
            Toast.makeText(this, "CitiesFrom length is " + arr[0].length() + " " +
                    "CitiesTo length is " +arr[1].length(), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
}
