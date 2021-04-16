package com.example.mycontact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, phone;
    Button addbtn , viewBtn;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        addbtn = findViewById(R.id.btnadd);
        viewBtn = findViewById(R.id.btnView);
        dbHelper = new DBHelper(MainActivity.this);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String txtname = name.getText().toString();
                String txtphone = phone.getText().toString();
                if(txtname.length()==0)
                {
                    name.requestFocus();
                    Toast.makeText(MainActivity.this, "ENTER NAME", Toast.LENGTH_SHORT).show();
//                    name.setError("FIELD CANNOT BE EMPTY");
                }
                else if(txtphone.length()==0)
                {
                    phone.requestFocus();
                    Toast.makeText(MainActivity.this, "ENTER CONTACT", Toast.LENGTH_SHORT).show();
//                    phone.setError("FIELD CANNOT BE EMPTY");
                }
                else
                {
                   Boolean checkdata = dbHelper.AddData(txtname, txtphone);
                    if (checkdata == true) {
                        Toast.makeText(MainActivity.this, "New Contact Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "No Contact Added", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContactActivity.class);
                startActivity(intent);

            }
        });

    }
}