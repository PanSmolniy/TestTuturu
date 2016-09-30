package com.smolianinov.app.testtuturu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.smolianinov.app.testtuturu.exp_list.CustomLinkedMap;
import com.smolianinov.app.testtuturu.exp_list.ExpListAdapter;
import com.smolianinov.app.testtuturu.R;

import java.util.ArrayList;
import java.util.List;


public class StationsFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stations_chooser_fragment, container, false);
        initializeListView(rootView, returnStations());

        return rootView;
    }

    private CustomLinkedMap<String, List<Object>> returnStations()
    {

        CustomLinkedMap<String, List<Object>> stations = new CustomLinkedMap<>();
        stations.put("Albania", null);
        stations.put("Armenia", null);
        stations.put("Cameroon", null);
        stations.put("Ukraine", null);
        return stations;
    }

    private void initializeListView(View view, CustomLinkedMap<String, List<Object>> stations)
    {
        ExpandableListView listView = (ExpandableListView)view.findViewById(R.id.expandableListView);

        ExpListAdapter adapter = new ExpListAdapter(getActivity().getApplicationContext(), stations);
        listView.setAdapter(adapter);
    }

    public static StationsFragment newInstance() {
        return new StationsFragment();
    }
}
