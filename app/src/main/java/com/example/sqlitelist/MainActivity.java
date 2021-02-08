package com.example.sqlitelist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        listView = findViewById(R.id.listview);
        db = new DBhelper(this);
        // INSERTING DATA, do once if database empty //
        /*db.insertData("Malaysia");
        db.insertData("Singapore");
        db.insertData("Brunei");
        db.insertData("Netherlands");*/

        //DELETING AND UPDATING DATA
        /*db.updateData(1, "Venice");
        db.updateData(2, "Spain");
        db.updateData(3, "Mauritius");
        db.deleteData(4);*/
        Cursor cursor = db.readData();
        //check if cursor got data or not//
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row, cursor,
                    new String[]{"recID", "recContent"},
                    new int[]{R.id.recID, R.id.recContent},
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String toast = ((Cursor) parent.getItemAtPosition(position)).getString(1);
                    Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
                }
            });
        }
        Cursor cp = getContentResolver().query(
                Uri.parse("content://com.example.sqlitelist.provider"),
                new String[]{"recID", "recContent"}, null, null, "recID"
        );
        if (cp.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {

            cp.moveToFirst();
            StringBuilder sb = new StringBuilder();
            while (!cp.isAfterLast()) {
                sb.append(cp.getString(0) + ":" + cp.getString(1) + "\n");
                cp.moveToNext();
            }
            textView.setText(sb.toString());
        }
    }

}

