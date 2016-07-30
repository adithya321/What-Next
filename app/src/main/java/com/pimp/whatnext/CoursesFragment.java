package com.pimp.whatnext;

import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

public class CoursesFragment extends android.support.v4.app.Fragment {

    public CoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String data = getActivity().getIntent().getStringExtra("DATA");
        String id = "";
        for (int i = 0; i < 6; i++) {
            if (data.charAt(i) == ',') {
                id = data.substring(0, i);
                break;
            }
        }

        CollegesDataSource datasource = new CollegesDataSource(getActivity().getApplicationContext());
        DataBaseHelper myDbHelper = new DataBaseHelper(getActivity().getApplicationContext());


        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        datasource = new CollegesDataSource(getActivity().getApplicationContext());
        datasource.open();

        String[] details = datasource.getCollegeDetails(id);

        TextView coursesTextView = (TextView) getActivity().findViewById(R.id.courses_TextView);
        coursesTextView.setText(details[15]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        return view;
    }
}