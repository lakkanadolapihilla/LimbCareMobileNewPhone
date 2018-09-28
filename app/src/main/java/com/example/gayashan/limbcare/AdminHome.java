package com.example.gayashan.limbcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AdminHome extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton imgbtngalley = findViewById(R.id.imageButton2);

        imgbtngalley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminGalley.class));
            }
        });

        ImageButton imgbtnourteam = findViewById(R.id.imageButton3);

        imgbtnourteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminOurTeam.class));
            }
        });

        ImageButton imgbtnservice = findViewById(R.id.imageButton);

        imgbtnservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminService.class));
            }
        });

        ImageButton imgadminnotice = findViewById(R.id.imageButton4);

        imgadminnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminNotice.class));
            }
        });
    }


}
