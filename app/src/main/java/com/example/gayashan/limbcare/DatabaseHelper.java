package com.example.gayashan.limbcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.sql.Types.BLOB;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "limbcare4";
    public static final String TABLE_NAME = "employee";
    public static final String EMP_ID = "emp_id";
    public static final String EMP_FNAME = "emp_fname";
    public static final String EMP_LNAME = "emp_lname";
    public static final String EMP_NIC = "emp_nic";
    public static final String EMP_JOB = "emp_job";
    public static final String EMP_EMAIL = "emp_email";
    public static final String EMP_BDAY = "emp_birthday";
    public static final String EMP_PHOTO = "emp_photo";


    public static final String NOTICE = "notice";
    public static final String NOTICE_ID = "notice_id";
    public static final String TOPIC = "topic";
    public static final String DESCRIPTION = "description";
    public static final String NOTICE_PHOTO = "notice_photo";

    public static final String GALLERY = "GALLERY";
    public static final String GALLERY_ID = "galley_id";
    public static final String GALLERY_TOPIC = "gallery_topic";
    public static final String GALLERY_DESCRIPTION = "gallery_description";
    public static final String GALLERY_PHOTO = "gallery_photo";

    public static final String SERVICE = "service";
    public static final String SERVICE_ID = "service_id";
    public static final String SERVICE_TOPIC = "service_topic";
    public static final String SERVICE_DESCRIPTION = "service_description";
    public static final String SERVICE_PHOTO = "service_photo";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 10);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(" create table "+TABLE_NAME+" (" +
                EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EMP_FNAME + " TEXT, " +
                EMP_LNAME + " TEXT, " +
                EMP_NIC + " TEXT, " +
                EMP_JOB + " TEXT, " +
                EMP_EMAIL + " TEXT, " +
                EMP_BDAY + " DATE, " +
                EMP_PHOTO + " BLOB)");

        db.execSQL(" create table "+NOTICE+" (" +
                NOTICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TOPIC + " TEXT, " +
                DESCRIPTION + " TEXT, " +
                NOTICE_PHOTO + " BLOB)");

        db.execSQL(" create table "+GALLERY+" (" +
                GALLERY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GALLERY_TOPIC + " TEXT, " +
                GALLERY_DESCRIPTION + " TEXT, " +
                GALLERY_PHOTO + " BLOB)");

        db.execSQL(" create table "+SERVICE+" (" +
                SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SERVICE_TOPIC + " TEXT, " +
                SERVICE_DESCRIPTION + " TEXT, " +
                SERVICE_PHOTO + " BLOB)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
