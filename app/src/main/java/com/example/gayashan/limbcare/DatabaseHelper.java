package com.example.gayashan.limbcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.sql.Types.BLOB;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "limbcare";
    public static final String TABLE_NAME = "employee";
    public static final String TABLE_COLUMN1 = "emp_id";
    public static final String TABLE_COLUMN2 = "emp_fname";
    public static final String TABLE_COLUMN3 = "emp_lname";
    public static final String TABLE_COLUMN4 = "emp_nic";
    public static final String TABLE_COLUMN5 = "emp_job";
    public static final String TABLE_COLUMN6 = "emp_email";
    public static final String TABLE_COLUMN7 = "emp_birthday";
    public static final String TABLE_COLUMN8 = "emp_photo";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+TABLE_NAME+" (" +
                TABLE_COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_COLUMN2 + " TEXT, " +
                TABLE_COLUMN3 + " TEXT, " +
                TABLE_COLUMN4 + " TEXT, " +
                TABLE_COLUMN5 + " TEXT, " +
                TABLE_COLUMN6 + " TEXT, " +
                TABLE_COLUMN7 + " DATE, " +
                TABLE_COLUMN8 + " BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
