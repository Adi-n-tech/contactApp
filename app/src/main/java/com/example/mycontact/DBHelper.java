package com.example.mycontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Userdetails(name TEXT primary key,contact TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");
    }

    public Boolean AddData(String name, String contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        long result = db.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
/*    public Boolean Updatedata(String name, String contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        long result = db.update("Userdetails", contentValues,"name = ?",new  String[]{name});
           if (result == -1) {
               return false;
           } else {
               return true;
           }

    }*/
public void Updatedata(String originalname,String name, String contact) {

    // calling a method to get writable database.
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();

    // on below line we are passing all values
    // along with its key and value pair.
    values.put("name", name);
    values.put("contact", contact);


    // on below line we are calling a update method to update our database and passing our values.
    // and we are comparing it with name of our course which is stored in original name variable.
    db.update("Userdetails", values, "name=?", new String[]{originalname});
    db.close();
}

 public Boolean Deletedata(String name) {
     SQLiteDatabase db = this.getWritableDatabase();
     Cursor cursor = db.rawQuery("Select * from Userdetails where name = ?",new String[]{name});
     if (cursor.getCount()>0) {
         long result = db.delete("Userdetails","name=?",new String[]{name});
         if (result == -1) {
             return false;
         } else {
             return true;
         }
     }
     else {
         return false;
     }
 }

  /*  public void Deletedata(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("Userdetails", "name" + "=" + id, null) ;
    }*/

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}
