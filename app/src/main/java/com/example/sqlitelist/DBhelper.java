package com.example.sqlitelist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public DBhelper(@Nullable Context context) {
        super(context, "myCountry.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE table1 (recID INTEGER PRIMARY KEY AUTOINCREMENT, recContent TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS table1");
        onCreate(db);

    }
    // ===== ADDING DATA====================================================
    public Boolean insertData(String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues(1); //only 1 content
        values.put("recContent", content);
        db.insert("table1", null, values);
        return true;
    }
    // ===== UPDATING DATA====================================================
    public Boolean updateData(int recID, String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues(1); //only 1 content
        values.put("recContent", content);
        db.update("table1", values, "recID=?", new String[]{String.valueOf(recID)});
        return true;
    }
    // ===== DELETE DATA====================================================
    public Boolean deleteData(int recID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("table1", "recID=?", new String[]{String.valueOf(recID)});
        return true;

    }
    // ===== READING DATA====================================================
    public Cursor readData(){
        String query = "SELECT recID AS _id, recID, recContent FROM table1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor =db.rawQuery(query, null);
        }
        return cursor;
    }








}
