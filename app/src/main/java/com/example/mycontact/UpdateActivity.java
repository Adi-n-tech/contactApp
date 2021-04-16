package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private Button updatebtn;
    private EditText updatecontact,updatename;
    private DBHelper dbHelper;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        updatename = findViewById(R.id.updatename);
        updatecontact = findViewById(R.id.updatecontact);
        updatebtn = findViewById(R.id.updatebtn);
        dbHelper = new DBHelper(UpdateActivity.this);
        String UpName = getIntent().getStringExtra("name");
        String UpContact = getIntent().getStringExtra("contact");

        // setting data to edit text
        // of our update activity.
        updatename.setText(UpName);
        updatecontact.setText(UpContact);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHelper.Updatedata(UpName,updatename.getText().toString(), updatecontact.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}