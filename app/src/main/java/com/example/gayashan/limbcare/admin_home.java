package com.example.gayashan.limbcare;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class admin_home extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton imgbtngalley = findViewById(R.id.imageButton2);

        imgbtngalley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_home.this, admin_galley.class));
            }
        });

        ImageButton imgbtnourteam = findViewById(R.id.imageButton3);

        imgbtnourteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_home.this, admin_our_team.class));
            }
        });

        ImageButton imgbtnservice = findViewById(R.id.imageButton);

        imgbtnservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_home.this, admin_service.class));
            }
        });

        ImageButton imgadminnotice = findViewById(R.id.imageButton4);

        imgadminnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_home.this, admin_notice.class));
            }
        });
    }


}
