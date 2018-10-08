package com.example.gayashan.limbcare;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteTeam extends AppCompatActivity {
    EditText insert_id_delete;
    DatabaseHelper mHelper;
    String dID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_team);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button btncancel,btn_delete;

        btn_delete=findViewById(R.id.btn_delete);
        insert_id_delete=findViewById(R.id.insert_id);
        mHelper = new DatabaseHelper(this);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_id_delete=findViewById(R.id.insert_id);
                dID=insert_id_delete.getText().toString();
                SQLiteDatabase db = mHelper.getReadableDatabase();
                int del=  db.delete("employee", "emp_id=?", new String[]{dID});

                if (del == 0) {
                    Toast.makeText(getApplicationContext(), "Team mebmer Not delete Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Team mebmer  delete Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
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
}
