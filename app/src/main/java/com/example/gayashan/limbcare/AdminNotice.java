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

import adapters.NoticeAdapter;
import adapters.TeamAdapter;

public class AdminNotice extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<NoticeCard> noticeCardList;


    DatabaseHelper mHelper;
    TextView topic, venue, date, time, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noticeCardList = new ArrayList<>();

        adapter = new NoticeAdapter(noticeCardList, this);

        recyclerView.setAdapter(adapter);

        FloatingActionButton fbtnnotice = findViewById(R.id.fab);

        fbtnnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNotice.this, AdminNoticeAdd.class));
            }
        });

        FloatingActionButton fbtnupdate = findViewById(R.id.fabupdate);

        fbtnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNotice.this, UpdateNotice.class));
            }
        });

        FloatingActionButton fbtndelete = findViewById(R.id.fabdelete);

        fbtndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNotice.this, DeleteNotice.class));
            }
        });

        mHelper = new DatabaseHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Cursor cursor2 = retrieveAllData();
        while (cursor2.moveToNext()) {

            noticeCardList.add(new NoticeCard(cursor2.getString(0), cursor2.getString(1),cursor2.getString(2),cursor2.getString(3),cursor2.getString(4),cursor2.getBlob(5)));
        }
        cursor2.close();
    }

    private Cursor retrieveAllData() {
        SQLiteDatabase db = mHelper.getReadableDatabase();


        String[] projection1 = {
                DatabaseHelper.TOPIC,
                DatabaseHelper.VENUE,
                DatabaseHelper.TIME,
                DatabaseHelper.DATE,
                DatabaseHelper.DESCRIPTION,
                DatabaseHelper.NOTICE_PHOTO

        };

        Cursor cursor1 = db.query(
                DatabaseHelper.NOTICE,   // The table to query
                projection1,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        return cursor1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHelper.close();
    }
}
