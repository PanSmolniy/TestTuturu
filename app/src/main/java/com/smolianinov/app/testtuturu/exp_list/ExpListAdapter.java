package com.smolianinov.app.testtuturu.exp_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smolianinov.app.testtuturu.R;

import java.util.List;

public class ExpListAdapter extends BaseExpandableListAdapter {

   // private ArrayList<ArrayList<String>> mGroups;
    private Context mContext;

    private CustomLinkedMap<String, List<Object>> stations;

    public ExpListAdapter (Context context, CustomLinkedMap<String, List<Object>> stations){
        mContext = context;
        //mGroups = groups;
        this.stations = stations;
    }



    @Override
    public int getGroupCount() {
        //return mGroups.size();
        return stations.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //return mGroups.get(groupPosition).size();
        return stations.getValue(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        //return mGroups.get(groupPosition);
        return stations.getKey(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //return mGroups.get(groupPosition).get(childPosition);
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

        if (isExpanded){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
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
        //textChild.setText(mGroups.get(groupPosition).get(childPosition));
       // textChild.setText(stations.getValue(groupPosition).get(childPosition));

        Button button = (Button)convertView.findViewById(R.id.buttonChild);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "button is pressed", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
