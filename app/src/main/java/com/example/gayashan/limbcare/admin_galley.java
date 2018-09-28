package com.example.gayashan.limbcare;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class admin_galley extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_galley);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fbtngalley = findViewById(R.id.fab);

        fbtngalley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_galley.this, admin_gallery_add.class));
            }
        });
    }


}
