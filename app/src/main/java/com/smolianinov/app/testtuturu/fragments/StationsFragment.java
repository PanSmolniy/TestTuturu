package com.smolianinov.app.testtuturu.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

    public AutoCompleteTextView from;
    public AutoCompleteTextView where;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stations_chooser_fragment, container, false);

        date = (TextView) rootView.findViewById(R.id.date_text);
        chooseDate = new ChooseDateDialog(getActivity(), this);
        setDateOnClick();

        dialog = new DetailedInfoDialog(getContext());
        parser = new JsonParser(getActivity());

        processJsonData(rootView);

        from = (AutoCompleteTextView) rootView.findViewById(R.id.from_actw);
        setAutocompleteAction(from, parser.getListFrom());
        where = (AutoCompleteTextView) rootView.findViewById(R.id.where_actw);
        setAutocompleteAction(where, parser.getListTo());

        return rootView;
    }

    private void setAutocompleteAction(AutoCompleteTextView view, List<String> stations)
    {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        view.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, stations));
    }

    private void setDateOnClick() {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate.show();;
            }
        });
    }

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

            CustomTreeMap<String, List<JSONObject>> stations;
            @Override
            protected Void doInBackground(Void... unusedParams) {
                // TODO: do your database stuff

                try {
                    stations = parser.orderData();
                    parser.fillStations(parser.listFrom, parser.stationsFrom);
                    parser.fillStations(parser.listTo, parser.stationsTo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                Log.d("AZAZA", parser.listFrom.size() + " " + parser.listTo.size());

                initializeListView(v, stations);

                super.onPostExecute(aVoid);
            }
        }.execute();

    }
}
