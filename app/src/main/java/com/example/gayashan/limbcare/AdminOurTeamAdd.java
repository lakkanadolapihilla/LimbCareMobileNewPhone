package com.example.gayashan.limbcare;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminOurTeamAdd extends AppCompatActivity {
    EditText editText3, editText4, editText5, editText6, editText7, editText8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_our_team_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btncancel = findViewById(R.id.button3);
        Button btnAdd = findViewById(R.id.btnAdd);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText8);



        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmpDatabaseHelper mHelper = new EmpDatabaseHelper(getApplicationContext());

                SQLiteDatabase db = mHelper.getWritableDatabase();

                if (editText3.getText().toString().equals("") || editText4.getText().toString().equals("") || editText5.getText().toString().equals("") || editText6.getText().toString().equals("") || editText7.getText().toString().equals("")|| editText8.getText().toString().equals("")) {
                    Toast.makeText(AdminOurTeamAdd.this, "Fields Cannot be empty!", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(EmpDatabaseHelper.TABLE_COLUMN2, editText3.getText().toString());
                    values.put(EmpDatabaseHelper.TABLE_COLUMN3, editText4.getText().toString());
                    values.put(EmpDatabaseHelper.TABLE_COLUMN4, editText5.getText().toString());
                    values.put(EmpDatabaseHelper.TABLE_COLUMN5, editText6.getText().toString());
                    values.put(EmpDatabaseHelper.TABLE_COLUMN6, editText7.getText().toString());
                    values.put(EmpDatabaseHelper.TABLE_COLUMN7, editText8.getText().toString());
                    long newRowId = db.insert(EmpDatabaseHelper.TABLE_NAME, null, values);

                    if (newRowId == 0) {
                        Toast.makeText(getApplicationContext(), "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

}