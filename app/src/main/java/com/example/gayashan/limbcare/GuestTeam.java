package com.example.gayashan.limbcare;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.TeamAdapter;

public class GuestTeam extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<TeamCard> teamCardList;

    DatabaseHelper mHelper;
    TextView id, fname, lname, nic, job, email, birthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_team);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        teamCardList = new ArrayList<>();

        adapter = new TeamAdapter(teamCardList, this);

        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Cursor cursor1 = retrieveAllData();
        while (cursor1.moveToNext()) {
//            id.setText(String.valueOf(cursor.getString(0)));
//            fname.setText(String.valueOf(cursor.getString(1)));
//            lname.setText(String.valueOf(cursor.getString(2)));
//            nic.setText(String.valueOf(cursor.getString(3)));
//            job.setText(String.valueOf(cursor.getString(4)));
//            email.setText(String.valueOf(cursor.getString(5)));
//            birthday.setText(String.valueOf(cursor.getString(6)));
            teamCardList.add(new TeamCard(cursor1.getString(0), cursor1.getString(1), cursor1.getString(2),cursor1.getString(3),cursor1.getString(4),cursor1.getString(5),cursor1.getString(6),cursor1.getBlob(7)));
        }
        cursor1.close();
    }

    private Cursor retrieveAllData() {
        SQLiteDatabase db1 = mHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DatabaseHelper.EMP_ID,
                DatabaseHelper.EMP_FNAME,
                DatabaseHelper.EMP_LNAME,
                DatabaseHelper.EMP_NIC,
                DatabaseHelper.EMP_JOB,
                DatabaseHelper.EMP_EMAIL,
                DatabaseHelper.EMP_BDAY,
        };
        Cursor cursor = db1.query(
                DatabaseHelper.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        return cursor;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHelper.close();
    }
}
