package com.example.gayashan.limbcare;

import android.content.ContentValues;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.NoticeAdapter;

public class GuestNotice extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<NoticeCard> noticeCardList;


    DatabaseHelper mHelper;
    TextView topic, description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_notice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noticeCardList = new ArrayList<>();

        adapter = new NoticeAdapter(noticeCardList, this);

        recyclerView.setAdapter(adapter);

        mHelper = new DatabaseHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Cursor cursor = retrieveAllData();
        while (cursor.moveToNext()) {

            noticeCardList.add(new NoticeCard(cursor.getString(0), cursor.getString(1),cursor.getBlob(2)));
        }
        cursor.close();
    }

    private Cursor retrieveAllData() {
        SQLiteDatabase db = mHelper.getReadableDatabase();


        String[] projection1 = {
                DatabaseHelper.TOPIC,
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