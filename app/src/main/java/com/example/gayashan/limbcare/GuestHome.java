package com.example.gayashan.limbcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GuestHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton imgbtngallery = findViewById(R.id.imageButton2);

        imgbtngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuestHome.this, GuestGalley.class));
            }
        });

        ImageButton imagebtnteam = findViewById(R.id.imageButton3);

        imagebtnteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuestHome.this, GuestTeam.class));
            }
        });

        ImageButton imgbtnservice = findViewById(R.id.imageButton);

        imgbtnservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuestHome.this, GuestService.class));
            }
        });

        ImageButton imgbtnnotice = findViewById(R.id.imageButton4);

        imgbtnnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuestHome.this, GuestNotice.class));
            }
        });
    }


}
