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
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateGallery extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    ImageView imageUpdatega;
    DatabaseHelper mHelper;
    public String IDgalary,uptopic,updescription;
    Cursor cursorgala;
    EditText insert_idGallery,updatetopicGallery,txtDescriptionGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_gallery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mHelper = new DatabaseHelper(this);

        Button btnimage,get_details,btn_update,btncancel;


        btncancel = findViewById(R.id.btn_cancel);

        btnimage= findViewById(R.id.btnimage);
        get_details= findViewById(R.id.get_details);
        btn_update= findViewById(R.id.btn_update);
        insert_idGallery= findViewById(R.id.insert_id);
        updatetopicGallery= findViewById(R.id.updatetopic);
        txtDescriptionGallery= findViewById(R.id.txtDescription);
        imageUpdatega= findViewById(R.id.imageUpdate);


        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        UpdateGallery.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
        get_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText updatetopicgallry,txtDescriptiongallery;

                IDgalary=insert_idGallery.getText().toString();
                updatetopicgallry= findViewById(R.id.updatetopic);
                txtDescriptiongallery= (EditText)findViewById(R.id.txtDescription);
                Cursor cursor = retrieveAllData();

                if (cursor.moveToNext()) {
                    uptopic=cursor.getString(1);
                    updescription= cursor.getString(2);
                    byte[] foodImage = cursor.getBlob(3);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
                    imageUpdatega.setImageBitmap(bitmap);
                    updatetopicgallry.setText(uptopic);
                    txtDescriptiongallery.setText(updescription);
                }
                else
                {
                    Toast.makeText(UpdateGallery.this, " Not valid ID!!!", Toast.LENGTH_SHORT).show();

                }
                cursor.close();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID,Topic,Description;
                byte[] img;

                insert_idGallery= findViewById(R.id.insert_id);
                updatetopicGallery= findViewById(R.id.updatetopic);
                txtDescriptionGallery= findViewById(R.id.txtDescription);
                imageUpdatega= findViewById(R.id.imageUpdate);
                ID=insert_idGallery.getText().toString();
                Topic=updatetopicGallery.getText().toString();
                Description=txtDescriptionGallery.getText().toString();
                img=imageViewToByte(imageUpdatega);
                ContentValues values = new ContentValues();

                ContentValues value = new ContentValues();
                value.put("topic", Topic);
                value.put("description", Description);
                value.put("photo", img);


                SQLiteDatabase db = mHelper.getReadableDatabase();
                String selection = "id = ?";
                int newRowId = db.update(
                        "GALLERY",
                        value,
                        selection,
                        new String[]{ID});
                if (newRowId == 0) {
                    Toast.makeText(getApplicationContext(), "Gallery Not Update Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Gallery  Update Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private Cursor retrieveAllData() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String SQLW="select * from GALLERY where id='" + IDgalary + "'";

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
                imageUpdatega.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
