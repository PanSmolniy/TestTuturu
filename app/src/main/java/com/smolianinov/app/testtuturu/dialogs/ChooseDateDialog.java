package com.smolianinov.app.testtuturu.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;

import android.widget.TextView;


import com.smolianinov.app.testtuturu.R;

import com.smolianinov.app.testtuturu.fragments.StationsFragment;


public class ChooseDateDialog extends Dialog implements View.OnClickListener
{
    private StationsFragment fragment;

    public ChooseDateDialog(Context context, StationsFragment fragment) {
        super(context);
        this.fragment = fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_date_popup);


        TextView set = (TextView) findViewById(R.id.set_date_tw);
        TextView discard = (TextView) findViewById(R.id.discard_tw);
        set.setOnClickListener(this);
        discard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.set_date_tw : {
                Log.d("Help", "Please");
                DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
                String res = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth()+1) +
                        "/" + datePicker.getYear();
                fragment.date.setText(res);
                dismiss();
            } break;

            case R.id.discard_tw:

                fragment.date.setText(getContext().getResources().getString(R.string.when));
                dismiss();
                break;
            default:
                break;
        }

    }





}
