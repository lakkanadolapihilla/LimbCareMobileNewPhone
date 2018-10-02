package com.example.gayashan.limbcare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.TeamAdapter;

public class AdminOurTeam extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<TeamCard> teamCardList;

    DatabaseHelper mHelper;
    TextView id, fname, lname, nic, job, email, birthday;
    ImageView imgteams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_our_team);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        teamCardList = new ArrayList<>();

        adapter = new TeamAdapter(teamCardList, this);

        recyclerView.setAdapter(adapter);

        FloatingActionButton fbtnourteam = findViewById(R.id.fab);

        fbtnourteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminOurTeam.this, AdminOurTeamAdd.class));
            }
        });

        mHelper = new DatabaseHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Cursor cursor = retrieveAllData();
        while (cursor.moveToNext()) {
            teamCardList.add(new TeamCard(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getBlob(7)));
        }
        cursor.close();
    }

    private Cursor retrieveAllData() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] projection = {
                DatabaseHelper.EMP_ID,
                DatabaseHelper.EMP_FNAME,
                DatabaseHelper.EMP_LNAME,
                DatabaseHelper.EMP_NIC,
                DatabaseHelper.EMP_JOB,
                DatabaseHelper.EMP_EMAIL,
                DatabaseHelper.EMP_BDAY,
                DatabaseHelper.EMP_PHOTO
        };

        Cursor cursor = db.query(
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
