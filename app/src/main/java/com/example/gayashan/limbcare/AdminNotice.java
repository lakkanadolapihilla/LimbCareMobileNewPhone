package com.example.gayashan.limbcare;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminNotice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fbtnnotice = findViewById(R.id.fab);

        fbtnnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNotice.this, AdminNoticeAdd.class));
            }
        });
    }
}
