package com.denystrush.habitsnew;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class CategoryDataBase extends SQLiteOpenHelper implements BaseColumns {

    private static final String DATABASE_NAME = "category_db.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "category_table";
    public static final String CATEGORY_NAME = "category_name";
    public static final String CATEGORY_DISCR = "category_discr";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " (" + CategoryDataBase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CATEGORY_NAME + " VARCHAR(64)," + CATEGORY_DISCR + " VARCHAR(255));";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

    public CategoryDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Обновление базы данных с версии " + oldVersion
                + " до версии " + newVersion + ", которое удалит все старые данные");
        // Удаляем предыдущую таблицу при апгрейде
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        // Создаём новый экземпляр таблицы
        onCreate(sqLiteDatabase);
    }
}
