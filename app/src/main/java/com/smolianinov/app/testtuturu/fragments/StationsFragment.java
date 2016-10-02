package com.smolianinov.app.testtuturu.fragments;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;


import com.smolianinov.app.testtuturu.dialogs.ChooseDateDialog;
import com.smolianinov.app.testtuturu.dialogs.DetailedInfoDialog;
import com.smolianinov.app.testtuturu.exp_list.CustomTreeMap;
import com.smolianinov.app.testtuturu.exp_list.ExpListAdapter;
import com.smolianinov.app.testtuturu.R;
import com.smolianinov.app.testtuturu.json_processor.JsonConstants;
import com.smolianinov.app.testtuturu.json_processor.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class StationsFragment extends Fragment {

    private JsonParser parser;
    private ExpandableListView listView;
    private DetailedInfoDialog dialog;
    private ChooseDateDialog chooseDate;
    public TextView date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stations_chooser_fragment, container, false);

        //date = (TextView) getContext().findViewById(R.id.date_text);
        date = (TextView) rootView.findViewById(R.id.date_text);
        chooseDate = new ChooseDateDialog(getActivity(), this);
        setDateOnClick();

        dialog = new DetailedInfoDialog(getContext());
        //initializeListView(rootView, returnStations());


        parser = new JsonParser(getActivity());

        processJsonData(rootView);


        return rootView;
    }

    private void setDateOnClick() {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               inflateDateDialog();
            }
        });
    }

    private void inflateDateDialog()
    {

        /*DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };*/

        /*AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.choose_date_popup, null);
        dialogBuilder.setView(dialogView);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();*/

        chooseDate.show();
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
        listView = (ExpandableListView)view.findViewById(R.id.expandableListView);


        ExpListAdapter adapter = new ExpListAdapter(getActivity().getApplicationContext(), stations);
        listView.setAdapter(adapter);
        setLVChildClickListener(adapter);
    }

    private void setLVChildClickListener(final ExpListAdapter adapter)
    {
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String arr[] = new String[4];

                JSONObject obj = (JSONObject) adapter.getChild(groupPosition, childPosition);
                try {
                    arr[0] = obj.getString(JsonConstants.STATION_NAME);
                    arr[1] = obj.getString(JsonConstants.STATION_CITY);
                    arr[2] = obj.getString(JsonConstants.STATION_REGION);
                    arr[3] = obj.getString(JsonConstants.STATION_COUNTRY);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.setArr(arr);
                //dialog.updateTextView();
                //dialog.update();

                dialog.show();
                return false;
            }
        });
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
