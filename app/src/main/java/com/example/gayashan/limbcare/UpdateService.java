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

public class UpdateService extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    ImageView imageUpdateService;
    DatabaseHelper mHelper;
    public String IDService,uptopicService,ServiceType,ServicePrice,Servicedescriptio;
    Cursor cursorgala;
    EditText insert_idService,updatetopicService,txtDescriptionService,ServiceTtpeUp,ServicePriceUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service);
        mHelper = new DatabaseHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button btnimage,get_details,btn_update,btncancel;
        imageUpdateService=findViewById(R.id.imageUpdate);
        insert_idService=findViewById(R.id.insert_id);
        updatetopicService=findViewById(R.id.updatetopic);
        ServiceTtpeUp=findViewById(R.id.updatetype);
        ServicePriceUp=findViewById(R.id.updateprice);
        txtDescriptionService=findViewById(R.id.txtDescription);
        btnimage=findViewById(R.id.btnimage);
        get_details=findViewById(R.id.get_detailst);
        btn_update=findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] img;
                imageUpdateService=findViewById(R.id.imageUpdate);
                insert_idService=findViewById(R.id.insert_id);
                updatetopicService=findViewById(R.id.updatetopic);
                ServiceTtpeUp=findViewById(R.id.updatetype);
                ServicePriceUp=findViewById(R.id.updateprice);
                txtDescriptionService=findViewById(R.id.txtDescription);

                IDService=insert_idService.getText().toString();
                uptopicService=updatetopicService.getText().toString();
                ServiceType=ServiceTtpeUp.getText().toString();
                ServicePrice=ServicePriceUp.getText().toString();
                Servicedescriptio=txtDescriptionService.getText().toString();
                img=imageViewToByte(imageUpdateService);

                ContentValues value = new ContentValues();

                value.put("topic", uptopicService);
                value.put("type", ServiceType);
                value.put("description", Servicedescriptio);
                value.put("price", ServicePrice);
                value.put("photo", img);

                SQLiteDatabase db = mHelper.getReadableDatabase();
                String selection = "id = ?";
                int newRowId = db.update(
                        "service",
                        value,
                        selection,
                        new String[]{IDService});
                if (newRowId == 0) {
                    Toast.makeText(getApplicationContext(), "Service Not Update Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Service  Update Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        get_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDService=insert_idService.getText().toString();
                Cursor cursor = retrieveAllData();
                if (cursor.moveToNext()) {
                    uptopicService=cursor.getString(1);
                    ServiceType=cursor.getString(2);
                    Servicedescriptio=cursor.getString(3);
                    ServicePrice=cursor.getString(4);
                    byte[] foodImage = cursor.getBlob(5);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
                    imageUpdateService.setImageBitmap(bitmap);
                    updatetopicService.setText(uptopicService);
                    txtDescriptionService.setText(Servicedescriptio);
                    ServiceTtpeUp.setText(ServiceType);
                    ServicePriceUp.setText(ServicePrice);
                }
                else{
                    Toast.makeText(UpdateService.this, " Not valid ID!!!", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });

        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        UpdateService.this,
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
        String SQLW="select * from service where id='" + IDService + "'";

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
                imageUpdateService.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
