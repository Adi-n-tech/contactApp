package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Modelclass> arrayList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        dbHelper = new DBHelper(this);

        recyclerView=findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new DBHelper(this).getData();

        arrayList=new ArrayList<>();
        while (cursor.moveToNext()) {
            Modelclass obj = new Modelclass(cursor.getString(0),cursor.getString(1));
            arrayList.add(obj);
        }
        CAdapter adapter = new CAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);


    }
}