package com.smolianinov.app.testtuturu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smolianinov.app.testtuturu.MainActivity;
import com.smolianinov.app.testtuturu.R;

/**
 * Created by PC on 27.09.2016.
 */
public class CopyrightFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.copyright_fragment, container, false);
        TextView version = (TextView) rootView.findViewById(R.id.version);
        version.setText(getString(R.string.version, getArguments().getString("Version")));
        return rootView;
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CopyrightFragment newInstance(String versionName) {
        CopyrightFragment fragment = new CopyrightFragment();
        Bundle args = new Bundle();
        args.putString("Version", versionName);
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
