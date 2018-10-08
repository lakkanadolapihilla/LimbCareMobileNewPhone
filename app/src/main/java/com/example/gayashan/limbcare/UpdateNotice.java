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

public class UpdateNotice extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    ImageView imageUpdatenotice;
    DatabaseHelper mHelper;
    public String IDnotice,uptopic,upnotice,vanunotice,updescription,uptime;
    Cursor cursorgala;
    EditText insert_idNote,updatetopicNote,txtDescriptionNote,upvenu,noteeDate,Uptime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mHelper = new DatabaseHelper(this);

        Button btnimage,get_details,btn_update,btncancel;
        btncancel = findViewById(R.id.btn_cancel);
        btnimage=findViewById(R.id.btnimage);
        get_details=findViewById(R.id.get_details);
        btn_update=findViewById(R.id.btn_update);

        insert_idNote=findViewById(R.id.insert_id);
        updatetopicNote=findViewById(R.id.uptopic);
        txtDescriptionNote=findViewById(R.id.txtDescription);
        upvenu=findViewById(R.id.upvenue);
        noteeDate=findViewById(R.id.updateda);
        Uptime=findViewById(R.id.uptime);
        imageUpdatenotice=findViewById(R.id.imageUpdate);

        get_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDnotice=insert_idNote.getText().toString();
                Cursor cursor = retrieveAllData();
                if (cursor.moveToNext()) {
                    uptopic=cursor.getString(1);
                    vanunotice=cursor.getString(2);
                    upnotice=cursor.getString(3);
                    uptime=cursor.getString(4);
                    updescription=cursor.getString(5);
                    byte[] foodImage = cursor.getBlob(6);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
                    imageUpdatenotice.setImageBitmap(bitmap);
                    updatetopicNote.setText(uptopic);
                    txtDescriptionNote.setText(updescription);
                    upvenu.setText(vanunotice);
                    noteeDate.setText(upnotice);
                    Uptime.setText(uptime);
                }
                else{
                    Toast.makeText(UpdateNotice.this, " Not valid ID!!!", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] img;
                insert_idNote=findViewById(R.id.insert_id);
                updatetopicNote=findViewById(R.id.uptopic);
                txtDescriptionNote=findViewById(R.id.txtDescription);
                upvenu=findViewById(R.id.upvenue);
                noteeDate=findViewById(R.id.updateda);
                Uptime=findViewById(R.id.uptime);
                imageUpdatenotice=findViewById(R.id.imageUpdate);

                IDnotice=insert_idNote.getText().toString();
                uptopic=updatetopicNote.getText().toString();
                upnotice=noteeDate.getText().toString();
                vanunotice=upvenu.getText().toString();
                updescription=txtDescriptionNote.getText().toString();
                uptime=Uptime.getText().toString();
                img=imageViewToByte(imageUpdatenotice);

                ContentValues value = new ContentValues();

                value.put("date", upnotice);
                value.put("venue", vanunotice);
                value.put("topic", uptopic);
                value.put("description", updescription);
                value.put("time", uptime);
                value.put("notice_photo", img);

                SQLiteDatabase db = mHelper.getReadableDatabase();
                String selection = "notice_id = ?";
                int newRowId = db.update(
                        "notice",
                        value,
                        selection,
                        new String[]{IDnotice});
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
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        UpdateNotice.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
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
        String SQLW="select * from notice where notice_id='" + IDnotice + "'";

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
                imageUpdatenotice.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
