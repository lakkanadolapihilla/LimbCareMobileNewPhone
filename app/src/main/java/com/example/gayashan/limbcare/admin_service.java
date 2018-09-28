package com.example.gayashan.limbcare;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class admin_service extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_service);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fbtnservice = findViewById(R.id.fab);

        fbtnservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_service.this, admin_service_add.class));
            }
        });
    }
}
