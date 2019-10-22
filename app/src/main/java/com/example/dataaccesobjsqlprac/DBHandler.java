package com.example.dataaccesobjsqlprac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "friendsDb";
    public static final String TABLE_FRIENDS = "friends";
    public static final String KEY_ID = "id";
    public static final String KEY_FNAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_EMAIL = "email";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableString = "CREATE TABLE " +
                TABLE_FRIENDS + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_FNAME + " TEXT, "+
                KEY_LNAME + " TEXT,"+ KEY_EMAIL + " TEXT" + ")";
        db.execSQL(createTableString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                TABLE_FRIENDS);
        onCreate(db);
    }
}