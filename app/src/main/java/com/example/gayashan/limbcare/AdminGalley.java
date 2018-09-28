package com.example.gayashan.limbcare;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminGalley extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_galley);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fbtngalley = findViewById(R.id.fab);

        fbtngalley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminGalley.this, AdminGalleryAdd.class));
            }
        });
    }


}
