package com.example.gayashan.limbcare;

import android.Manifest;
import android.content.ContentValues;
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

public class UpdateTeam extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    ImageView imageUpdateTeam;
    DatabaseHelper mHelper;
    public String fnameup,lanameup,nicup,titleup,emailup,birthup,teamIdup;
    Cursor cursorgala;
    EditText updatefnameUP,insert_idUP,updatelnameUp,updatenicUp,updatejobUp,updateemailUp,updatebirthUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_team);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mHelper = new DatabaseHelper(this);

        Button btnimage,get_details,btn_update,btncancel;
        imageUpdateTeam=findViewById(R.id.imageUpdate);
        updatefnameUP=findViewById(R.id.updatefname);
        insert_idUP=findViewById(R.id.insert_id);
        updatenicUp=findViewById(R.id.updatenic);
        updatejobUp=findViewById(R.id.updatejob);
        updateemailUp=findViewById(R.id.updateemail);
        updatebirthUp=findViewById(R.id.updatebirth);
        updatelnameUp=findViewById(R.id.updatelname);
        btnimage=findViewById(R.id.btnimage);
        get_details=findViewById(R.id.get_details);
        btn_update=findViewById(R.id.btn_update);

        get_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamIdup=insert_idUP.getText().toString();
                Cursor cursor = retrieveAllData();
                String fnameup,lanameup,nicup,titleup,emailup,birthup,teamIdup;
                if (cursor.moveToNext()) {
                    fnameup=cursor.getString(1);
                    lanameup=cursor.getString(2);
                    nicup=cursor.getString(3);
                    titleup=cursor.getString(4);
                    emailup=cursor.getString(5);
                    birthup=cursor.getString(6);
                    byte[] foodImage = cursor.getBlob(7);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
                    imageUpdateTeam.setImageBitmap(bitmap);
                    updatefnameUP.setText(fnameup);
                    updatelnameUp.setText(lanameup);
                    updatenicUp.setText(nicup);
                    updatejobUp.setText(titleup);
                    updateemailUp.setText(emailup);
                    updatebirthUp.setText(birthup);
                }
                else{
                    Toast.makeText(UpdateTeam.this, " Not valid ID!!!", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] img;

                imageUpdateTeam=findViewById(R.id.imageUpdate);
                updatefnameUP=findViewById(R.id.updatefname);
                insert_idUP=findViewById(R.id.insert_id);
                updatenicUp=findViewById(R.id.updatenic);
                updatejobUp=findViewById(R.id.updatejob);
                updateemailUp=findViewById(R.id.updateemail);
                updatebirthUp=findViewById(R.id.updatebirth);
                updatelnameUp=findViewById(R.id.updatelname);

                fnameup=updatefnameUP.getText().toString();
                lanameup=updatelnameUp.getText().toString();
                teamIdup=insert_idUP.getText().toString();
                nicup=updatenicUp.getText().toString();
                titleup=updatejobUp.getText().toString();
                emailup=updateemailUp.getText().toString();
                birthup=updatebirthUp.getText().toString();
                img=imageViewToByte(imageUpdateTeam);

                ContentValues value = new ContentValues();

                value.put("emp_fname", fnameup);
                value.put("emp_lname", lanameup);
                value.put("emp_nic", nicup);
                value.put("emp_job", titleup);
                value.put("emp_email", emailup);
                value.put("emp_birthday", birthup);
                value.put("emp_photo", img);

                SQLiteDatabase db = mHelper.getReadableDatabase();
                String selection = "emp_id = ?";
                int newRowId = db.update(
                        "employee",
                        value,
                        selection,
                        new String[]{teamIdup});
                if (newRowId == 0) {
                    Toast.makeText(getApplicationContext(), "Notice Not Update Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Notice  Update Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        UpdateTeam.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
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

    private Cursor retrieveAllData() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String SQLW="select * from employee where emp_id='" + teamIdup + "'";

        cursorgala = db.rawQuery(SQLW,null);

        return cursorgala;
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageUpdateTeam.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
