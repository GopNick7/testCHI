package com.github.gopnick.testapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "CHI";
    private static final int VERSION = 1;

    private static final String TABLE_NAME = "testForCHI";
    private static final String ID = "id";
    public static final String COUNTER = "counter";

    private SQLiteDatabase sqLiteDatabase;

    public Database(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "CREATE TABLE " + TABLE_NAME +
                " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COUNTER + " INTEGER NOT NULL " +
                ")";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(int counter) {
        ContentValues cv = new ContentValues();
        cv.put(COUNTER, counter);
        return sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    public Cursor showCounter() {

        String sql = "SELECT * FROM " + TABLE_NAME;

        return sqLiteDatabase.rawQuery(sql, null);
    }

    public void openDB() {
        sqLiteDatabase = getWritableDatabase();
    }

    public void closeDB() {
        if (sqLiteDatabase == null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }
}
