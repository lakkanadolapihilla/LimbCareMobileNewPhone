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

import adapters.GalleryAdapter;

public class GuestGalley extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<GalleryCard> galleryCardList;


    DatabaseHelper mHelper;
    TextView topic, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_galley);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        galleryCardList = new ArrayList<>();

        adapter = new GalleryAdapter(galleryCardList, this);

        recyclerView.setAdapter(adapter);

        mHelper = new DatabaseHelper(this);
    }

    @Override

    protected void onStart() {
        super.onStart();

        Cursor cursor = retrieveAllData();
        while (cursor.moveToNext()) {

            galleryCardList.add(new GalleryCard(cursor.getString(0),cursor.getString(1), cursor.getString(2),cursor.getBlob(3)));
        }
        cursor.close();
    }

    private Cursor retrieveAllData() {
        SQLiteDatabase db = mHelper.getReadableDatabase();


        String[] projection1 = {
                DatabaseHelper.GALLERY_ID,
                DatabaseHelper.GALLERY_TOPIC,
                DatabaseHelper.GALLERY_DESCRIPTION,
                DatabaseHelper.GALLERY_PHOTO


        };

        Cursor cursor1 = db.query(
                DatabaseHelper.GALLERY,   // The table to query
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
