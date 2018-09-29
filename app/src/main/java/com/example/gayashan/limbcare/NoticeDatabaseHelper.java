package com.example.gayashan.limbcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class NoticeDatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "limbcare";
    public static final String TABLE_NAME = "notice";
    public static final String TOPIC = "emp_id";
    public static final String DESCRIPTION = "description";
    public static final String NOTICE_PHOTO = "notice_photo";



    public NoticeDatabaseHelper(Context context) {super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+TABLE_NAME+" (" +
                TOPIC + " TEXT, " +
                DESCRIPTION + " TEXT, " +
                NOTICE_PHOTO + " BLOB)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
