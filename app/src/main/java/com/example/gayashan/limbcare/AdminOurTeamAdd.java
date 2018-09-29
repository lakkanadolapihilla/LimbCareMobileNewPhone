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
    EditText editTextFName, editTextLname, editTextNIC, editTextJob, editTextEMail, editTextBDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_our_team_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btncancel = findViewById(R.id.btnCancel);
        Button btnAdd = findViewById(R.id.btnAdd);
        editTextFName = findViewById(R.id.txtFName);
        editTextLname = findViewById(R.id.txtLName);
        editTextNIC = findViewById(R.id.txtNIC);
        editTextJob = findViewById(R.id.txtJob);
        editTextEMail = findViewById(R.id.txtEMail);
        editTextBDay = findViewById(R.id.txtBday);



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

                if (editTextFName.getText().toString().equals("") || editTextLname.getText().toString().equals("") || editTextNIC.getText().toString().equals("") || editTextJob.getText().toString().equals("") || editTextEMail.getText().toString().equals("")|| editTextBDay.getText().toString().equals("")) {
                    Toast.makeText(AdminOurTeamAdd.this, "Fields Cannot be empty!", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(EmpDatabaseHelper.EMP_FNAME, editTextFName.getText().toString());
                    values.put(EmpDatabaseHelper.EMP_LNAME, editTextLname.getText().toString());
                    values.put(EmpDatabaseHelper.EMP_NIC, editTextNIC.getText().toString());
                    values.put(EmpDatabaseHelper.EMP_JOB, editTextJob.getText().toString());
                    values.put(EmpDatabaseHelper.EMP_EMAIL, editTextEMail.getText().toString());
                    values.put(EmpDatabaseHelper.EMP_BDAY, editTextBDay.getText().toString());
                    long newRowId = db.insert(EmpDatabaseHelper.LIMBCARE, null, values);

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