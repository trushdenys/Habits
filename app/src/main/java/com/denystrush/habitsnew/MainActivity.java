package com.denystrush.habitsnew;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализируем наш класс-обёртку
        CategoryDataBase categoryDataBase = new CategoryDataBase(this);

        // База нам нужна для записи и чтения
        SQLiteDatabase sqLiteDatabase = categoryDataBase.getWritableDatabase();

        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + categoryDataBase.TABLE_NAME +";", null);

        if(cursor.getCount()==0) {
            setContentView(R.layout.main);
            sqLiteDatabase.close();
            categoryDataBase.close();

        } else {
            setContentView(R.layout.categ);
            String query = "SELECT " + categoryDataBase._ID + ", "
                    + categoryDataBase.CATEGORY_NAME + " FROM " + categoryDataBase.TABLE_NAME;
            Cursor csCategory = sqLiteDatabase.rawQuery(query, null);

            ArrayList<String> labels = new ArrayList<String>();
            while (csCategory.moveToNext()) {
                // GET COLUMN INDICES + VALUES OF THOSE COLUMNS
                int id = csCategory.getInt(csCategory.getColumnIndex(categoryDataBase._ID));
                String name = csCategory.getString(csCategory
                        .getColumnIndex(categoryDataBase.CATEGORY_NAME));
                Log.i("LOG_TAG", "ROW " + id + " HAS NAME " + name);
                labels.add(name);
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, labels);

            // Drop down layout style - list view with radio button
            dataAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);

            sqLiteDatabase.close();
            categoryDataBase.close();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onNewCategClick(View view) {
        Intent intent = new Intent(MainActivity.this, NewCategActivity.class);
        startActivity(intent);
    }
}
