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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.TeamAdapter;

public class AdminOurTeam extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<TeamCard> teamCardList;

    EmpDatabaseHelper mHelper;
    TextView id, fname, lname, nic, job, email, birthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_our_team);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        teamCardList = new ArrayList<>();

//        for (int i = 0; i<10;i++){
//            TeamCard teamcards = new TeamCard(
//                    "gaya",
//                    "madu",
//                    "jhwgsef",
//                    "ksfje",
//                    "iqrhf",
//                    "aksjf");
//
//            teamCardList.add(teamcards);

//
//        }

        adapter = new TeamAdapter(teamCardList, this);

        recyclerView.setAdapter(adapter);
//
//        id = findViewById(R.id.)
//        fname = findViewById(R.id.editText3);
//        lname = findViewById(R.id.editText4);
//        nic = findViewById(R.id.editText5);
//        job = findViewById(R.id.editText6);
//        email = findViewById(R.id.editText7);
//        birthday = findViewById(R.id.editText8);

        FloatingActionButton fbtnourteam = findViewById(R.id.fab);

        fbtnourteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminOurTeam.this, AdminOurTeamAdd.class));
            }
        });

        mHelper = new EmpDatabaseHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Cursor cursor = retrieveAllData();
        while (cursor.moveToNext()) {
//            id.setText(String.valueOf(cursor.getString(0)));
//            fname.setText(String.valueOf(cursor.getString(1)));
//            lname.setText(String.valueOf(cursor.getString(2)));
//            nic.setText(String.valueOf(cursor.getString(3)));
//            job.setText(String.valueOf(cursor.getString(4)));
//            email.setText(String.valueOf(cursor.getString(5)));
//            birthday.setText(String.valueOf(cursor.getString(6)));
            teamCardList.add(new TeamCard(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
        }
        cursor.close();
    }

    private Cursor retrieveAllData() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                EmpDatabaseHelper.EMP_ID,
                EmpDatabaseHelper.EMP_FNAME,
                EmpDatabaseHelper.EMP_LNAME,
                EmpDatabaseHelper.EMP_NIC,
                EmpDatabaseHelper.EMP_JOB,
                EmpDatabaseHelper.EMP_EMAIL,
                EmpDatabaseHelper.EMP_BDAY,
        };

        // Filter results WHERE "title" = 'My Title'
//        String selection = StudyMateContractor.NoteEntry.COLUMN_NAME_TITLE + " = ?";
        //String[] selectionArgs = { "How to Code" };

        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                StudyMateContractor.NoteEntry._ID + " DESC";

        Cursor cursor = db.query(
                EmpDatabaseHelper.TABLE_NAME,   // The table to query
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
