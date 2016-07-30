package com.pimp.whatnext;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CollegesDataSource {

    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public CollegesDataSource(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public String[] getCollegeDetails(String id) {
        String[] details = new String[20];

        String query = "SELECT * FROM " + DataBaseHelper.TABLE_COLLEGES + " WHERE " + DataBaseHelper.COLUMN_ID + "=" + id;

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        for (int i = 0; i < 16; i++) {
            details[i] = cursor.getString(i);
        }

        return details;
    }

    public List<College> findCollege(String category, String institutionType, String collegeType, String district, String university) {

        List<College> colleges = new ArrayList<College>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM " + DataBaseHelper.TABLE_COLLEGES + " WHERE ");

        int i = 0;
        if (category != "College Category : All") {
            queryBuilder.append(DataBaseHelper.COLUMN_CATEGORY + " = '" + category + "'");
            i++;
        }
        if (institutionType != "Institution Type : All") {
            if (i > 0) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(DataBaseHelper.COLUMN_INSTITUTION_TYPE + " = '" + institutionType + "'");
            i++;
        }
        if (collegeType != "College Type : All") {
            if (i > 0) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(DataBaseHelper.COLUMN_COLLEGE_TYPE + " = '" + collegeType + "'");
            i++;
        }
        if (district != "District : All") {
            if (i > 0) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(DataBaseHelper.COLUMN_DISTRICT + " = '" + district + "'");
            i++;
        }
        if (university != "Affiliated University : All") {
            if (i > 0) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(DataBaseHelper.COLUMN_UNIV + " = '" + university + "'");
            i++;
        }

        queryBuilder.append(" ORDER BY " + DataBaseHelper.COLUMN_NAME + " ASC");

        if (i == 0) {
            queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT * FROM " + DataBaseHelper.TABLE_COLLEGES + " ORDER BY " + DataBaseHelper.COLUMN_NAME + " ASC");
        }

        String query = queryBuilder.toString();

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            College college = cursorToCollege(cursor);
            colleges.add(college);
            cursor.moveToNext();
        }
        cursor.close();
        return colleges;
    }

    private College cursorToCollege(Cursor cursor) {
        College college = new College();
        college.setId(cursor.getString(0));
        college.setName(cursor.getString(1));
        return college;
    }
}
