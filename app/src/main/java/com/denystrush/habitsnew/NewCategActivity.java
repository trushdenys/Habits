package com.denystrush.habitsnew;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewCategActivity extends Activity {

    private EditText mEditCategName;
/*    private EditText mEditCategDiscr;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcateg);
    }

    public void onDeclButtonClick(View v) {
        finish();
    }

    public void onSaveButtonClick(View v) {

        // Инициализируем наш класс-обёртку
        CategoryDataBase categoryDataBase = new CategoryDataBase(this);

        // База нам нужна для записи и чтения
        SQLiteDatabase sqLiteDatabase = categoryDataBase.getWritableDatabase();


        mEditCategName = (EditText)findViewById(R.id.editNameCateg);
/*        mEditCategDiscr = (EditText)findViewById(R.id.editDiscrCateg);*/

        ContentValues contentValues = new ContentValues();
        contentValues.put(categoryDataBase.CATEGORY_NAME, mEditCategName.getText().toString());
/*        contentValues.put(categoryDataBase.CATEGORY_DISCR, mEditCategDiscr.getText().toString());*/
        sqLiteDatabase.insert(categoryDataBase.TABLE_NAME, categoryDataBase.CATEGORY_NAME, contentValues);
        mEditCategName.setText("");
/*        mEditCategDiscr.setText("");*/

        // закрываем соединения с базой данных
        sqLiteDatabase.close();
        categoryDataBase.close();
    }

}
