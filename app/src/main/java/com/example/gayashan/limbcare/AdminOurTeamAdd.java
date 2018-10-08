package com.example.gayashan.limbcare;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
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

public class AdminOurTeamAdd extends AppCompatActivity {
    EditText editTextFName, editTextLname, editTextNIC, editTextJob, editTextEMail, editTextBDay;
    Button btnAdd,pickImagea;
    ImageView pickimageview;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_our_team_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btncancel = findViewById(R.id.btnCancel);

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });


        pickimageview = findViewById(R.id.pickimageviewe);
        pickImagea = findViewById(R.id.pickImage);
        btnAdd = findViewById(R.id.btnAdd);
        editTextFName = findViewById(R.id.txtFName);
        editTextLname = findViewById(R.id.txtLName);
        editTextNIC = findViewById(R.id.txtNIC);
        editTextJob = findViewById(R.id.txtJob);
        editTextEMail = findViewById(R.id.txtEMail);
        editTextBDay = findViewById(R.id.txtBday);



        pickImagea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AdminOurTeamAdd.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper mHelper = new DatabaseHelper(getApplicationContext());

                SQLiteDatabase db = mHelper.getWritableDatabase();

                if (editTextFName.getText().toString().equals("") || editTextLname.getText().toString().equals("") || editTextNIC.getText().toString().equals("") || editTextJob.getText().toString().equals("") || editTextEMail.getText().toString().equals("")|| editTextBDay.getText().toString().equals("")) {
                    Toast.makeText(AdminOurTeamAdd.this, "Fields Cannot be empty!", Toast.LENGTH_SHORT).show();
                }

                if (editTextFName.getText().toString().equals("")){
                    Toast.makeText(AdminOurTeamAdd.this, "First name Fields Cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                if (editTextLname.getText().toString().equals("")){
                    Toast.makeText(AdminOurTeamAdd.this, "Last name Fields Cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                if (editTextNIC.getText().toString().equals("")){
                    Toast.makeText(AdminOurTeamAdd.this, "NIC Fields Cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                if (editTextJob.getText().toString().equals("")){
                    Toast.makeText(AdminOurTeamAdd.this, "Job tittle Fields Cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.EMP_FNAME, editTextFName.getText().toString());
                    values.put(DatabaseHelper.EMP_LNAME, editTextLname.getText().toString());
                    values.put(DatabaseHelper.EMP_NIC, editTextNIC.getText().toString());
                    values.put(DatabaseHelper.EMP_JOB, editTextJob.getText().toString());
                    values.put(DatabaseHelper.EMP_EMAIL, editTextEMail.getText().toString());
                    values.put(DatabaseHelper.EMP_BDAY, editTextBDay.getText().toString());
                    values.put(DatabaseHelper.EMP_PHOTO, imageViewToByte(pickimageview));
                    long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);

                    if (newRowId == 0) {
                        Toast.makeText(getApplicationContext(), "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
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
                pickimageview.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}