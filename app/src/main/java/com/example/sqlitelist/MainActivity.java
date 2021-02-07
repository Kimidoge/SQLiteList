package com.example.sqlitelist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView output;
    DBhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);
        db = new DBhelper(this);
        // INSERTING DATA, do once if database empty //
        /*db.insertData("Malaysia");
        db.insertData("Singapore");
        db.insertData("Brunei");
        db.insertData("Netherlands");*/

        //DELETING AND UPDATING DATA
        db.updateData(1, "Venice");
        db.updateData(2, "Spain");
        db.updateData(3, "Mauritius");
        db.deleteData(4);
        Cursor cursor = db.readData();
        //check if cursor got data or not//
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            StringBuilder sb = new StringBuilder();
            while(cursor.moveToNext()){
                sb.append(cursor.getString(0)+":"+cursor.getString(1)+"\n");
            }
            output.setText(sb.toString());
        }
    }
}