package com.example.gayashan.limbcare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DeleteService extends AppCompatActivity {
    EditText insert_id_delete;
    DatabaseHelper mHelper;
    String dID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button btncancel,btn_delete;

        btn_delete=findViewById(R.id.btn_delete);
        insert_id_delete=findViewById(R.id.insert_id);
        mHelper = new DatabaseHelper(this);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_id_delete=findViewById(R.id.insert_id);
                dID=insert_id_delete.getText().toString();
                SQLiteDatabase db = mHelper.getReadableDatabase();
                int del=  db.delete("service", "id=?", new String[]{dID});

                if (del == 0) {
                    Toast.makeText(getApplicationContext(), "Gallery Not delete Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Gallery  delete Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


         btncancel = findViewById(R.id.btn_cancel);

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
