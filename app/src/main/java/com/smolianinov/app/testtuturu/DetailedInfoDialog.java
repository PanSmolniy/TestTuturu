package com.smolianinov.app.testtuturu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class DetailedInfoDialog extends Dialog
{

    private String [] arr;
    private TextView view1;
    private TextView view2;
    private TextView view3;
    private TextView view4;
    public DetailedInfoDialog(Context context) {
        super(context);
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_info_dialog);

        view1 = (TextView) findViewById(R.id.station_name);


        view2 = (TextView) findViewById(R.id.station_city);


        view3 = (TextView) findViewById(R.id.station_region);


        view4 = (TextView) findViewById(R.id.station_country);




    }

    @Override
    public void show() {
        super.show();
        updateTextView();
    }

    private void updateTextView()
    {
        view1.setText(getContext().getString(R.string.station_name, arr[0]));
        view2.setText(getContext().getString(R.string.station_city, arr[1]));
        view3.setText(getContext().getString(R.string.station_region, arr[2]));
        view4.setText(getContext().getString(R.string.station_country, arr[3]));
    }
}
