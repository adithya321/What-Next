package com.pimp.whatnext;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.List;

public class HomeActivity extends ActionBarActivity {

    DataBaseHelper myDbHelper = new DataBaseHelper(this);
    Spinner course_type_spinner, courses_spinner, departments_spinner, district_spinner, university_spinner,
            institution_type_spinner, college_type_spinner, college_category_spinner;
    ListView colleges_list_view;
    String[] course_type_array = {"Course Type : All", "Under Graduate", "Post Graduate", "Diploma", "Post Diploma",
            "Research", "Certificate Course"};
    String[] courses_array = {"Course : All", "B.Arch", "B.E.", "B.Sc.", "B.Tech"};
    String[] departments_array = {"Department : All", "EEE", "CSE", "ECE"};
    String[] districts_array = {"District : All", "Ariyalur", "Chennai", "Coimbatore", "Cuddalore",
            "Dharmapuri", "Dindigul", "Erode", "Kanchipuram", "Kanyakumari", "Karur", "Krishnagiri",
            "Madurai", "Nagapattinam", "Namakkal", "Nilgiris", "Perambalur", "Pudukottai", "Ramnad",
            "Salem", "Sivagangai", "Thanjavur", "Theni", "Tirunelveli", "Tirupur", "Tiruvallur",
            "Tiruvanamalai", "Tiruvarur", "Trichy", "Tuticorin", "Vellore", "Villupuram", "Virudhunagar"};
    String[] university_array = {"Affiliated University : All", "Alagappa University", "Anna University",
            "Anna University - Coimbatore", "Anna University - Tirunelveli", "Anna University - Trichy",
            "Annamalai University", "Bharathidasan University", "Bharathiyar University", "Deemed University",
            "Madurai Kamaraj University", "Manonmaniam Sundaranar University", "Mother Teresa Women's University",
            "Periyar University", "Tamil Nadu Dr. M.G.R. Medical University",
            "Tamil Nadu Physical Education and Sports University", "Tamil Nadu Teachers Education University",
            "Tiruvalluvar University", "University of Madras"};
    String[] collge_category_array = {"College Category : All", "Arts & Science", "Ayurveda", "Dental", "Engineering",
            "Homoeopathy", "Medical", "Nursing", "Occupational Therapy", "Pharmacy", "Physiotherapy", "Siddha",
            "Teacher Education"};
    String[] institution_type_array = {"Institution Type : All", "Government", "Aided", "Self Finance"};
    String[] college_type_array = {"College Type : All", "Co-Education", "Mens", "Womens"};
    String categoryValue, institutionTypeValue, collegeTypeValue, districtValue, universityValue;
    private CollegesDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

        datasource = new CollegesDataSource(this);
        datasource.open();

        colleges_list_view = (ListView) findViewById(R.id.colleges_list_view);
        colleges_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String data = colleges_list_view.getItemAtPosition(position).toString();
                Intent intent = new Intent();
                intent.setClass(view.getContext(), DetailActivity.class);
                intent.putExtra("DATA", data);
                startActivity(intent);
            }
        });

        college_category_spinner = (Spinner) findViewById(R.id.college_category_spinner);
        ArrayAdapter<String> collegeCategoryAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, collge_category_array);
        collegeCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        college_category_spinner.setAdapter(collegeCategoryAdapter);
        college_category_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                college_category_spinner.setSelection(position);
                setListViewAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        institution_type_spinner = (Spinner) findViewById(R.id.institution_type_spinner);
        ArrayAdapter<String> institutionTypeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, institution_type_array);
        institutionTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        institution_type_spinner.setAdapter(institutionTypeAdapter);
        institution_type_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                institution_type_spinner.setSelection(position);
                setListViewAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        college_type_spinner = (Spinner) findViewById(R.id.college_type_spinner);
        ArrayAdapter<String> collegeTypeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, college_type_array);
        collegeTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        college_type_spinner.setAdapter(collegeTypeAdapter);
        college_type_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                college_type_spinner.setSelection(position);
                setListViewAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        district_spinner = (Spinner) findViewById(R.id.district_spinner);
        ArrayAdapter<String> adapter_districts = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, districts_array);
        adapter_districts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district_spinner.setAdapter(adapter_districts);
        district_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district_spinner.setSelection(position);
                setListViewAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        university_spinner = (Spinner) findViewById(R.id.university_spinner);
        ArrayAdapter<String> universityArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, university_array);
        universityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        university_spinner.setAdapter(universityArrayAdapter);
        university_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                university_spinner.setSelection(position);

                List<College> values = datasource.findCollege(categoryValue, institutionTypeValue, collegeTypeValue,
                        districtValue, universityValue);
                ArrayAdapter<College> collegeArrayAdapter = new ArrayAdapter<College>(getBaseContext(),
                        android.R.layout.simple_list_item_1, values);

                AutoCompleteTextView search = (AutoCompleteTextView) findViewById(R.id.search_bar);
                search.setThreshold(0);
                search.setAdapter(collegeArrayAdapter);
                search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String data = parent.getItemAtPosition(position).toString();
                        Intent intent = new Intent();
                        intent.setClass(view.getContext(), DetailActivity.class);
                        intent.putExtra("DATA", data);
                        startActivity(intent);
                    }
                });

                setListViewAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Intent intent = new Intent();
            intent.setClass(getBaseContext(), AboutActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setListViewAdapter() {
        categoryValue = college_category_spinner.getSelectedItem().toString();
        institutionTypeValue = institution_type_spinner.getSelectedItem().toString();
        collegeTypeValue = college_type_spinner.getSelectedItem().toString();
        districtValue = district_spinner.getSelectedItem().toString();
        universityValue = university_spinner.getSelectedItem().toString();

        List<College> values = datasource.findCollege(categoryValue, institutionTypeValue, collegeTypeValue,
                districtValue, universityValue);
        ArrayAdapter<College> collegeArrayAdapter = new ArrayAdapter<College>(getBaseContext(),
                android.R.layout.simple_list_item_1, values);
        colleges_list_view.setAdapter(collegeArrayAdapter);
    }
}
