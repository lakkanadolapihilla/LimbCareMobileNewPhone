package com.example.gayashan.limbcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.sql.Types.BLOB;

public class EmpDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "limbcare";
    public static final String LIMBCARE = "employee";
    public static final String EMP_ID = "emp_id";
    public static final String EMP_FNAME = "emp_fname";
    public static final String EMP_LNAME = "emp_lname";
    public static final String EMP_NIC = "emp_nic";
    public static final String EMP_JOB = "emp_job";
    public static final String EMP_EMAIL = "emp_email";
    public static final String EMP_BDAY = "emp_birthday";
    public static final String EMP_PHOTO = "emp_photo";

    public EmpDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+LIMBCARE+" (" +
                EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EMP_FNAME + " TEXT, " +
                EMP_LNAME + " TEXT, " +
                EMP_NIC + " TEXT, " +
                EMP_JOB + " TEXT, " +
                EMP_EMAIL + " TEXT, " +
                EMP_BDAY + " DATE, " +
                EMP_PHOTO + " BLOB)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
