package com.smolianinov.app.testtuturu.exp_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.smolianinov.app.testtuturu.R;
import com.smolianinov.app.testtuturu.json_processor.JsonConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class ExpListAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    private CustomTreeMap<String, List<JSONObject>> stations;

    public ExpListAdapter (Context context, CustomTreeMap<String, List<JSONObject>> stations){
        mContext = context;
        this.stations = stations;
    }



    @Override
    public int getGroupCount() {
        return stations.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return stations.getValue(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return stations.getKey(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return stations.getValue(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_view, null);
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        textGroup.setText(stations.getKey(groupPosition));

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_view, null);
        }

        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
        try {
            textChild.setText(stations.getValue(groupPosition).get(childPosition).getString(JsonConstants.STATION_NAME));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
