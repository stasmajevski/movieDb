package com.example.user.moviedb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.moviedb.model.MovieList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "MyDatabase";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MOVIES = "movies";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_title = "title";
    public static final String COLUMN_description = "description";
    public static final String COLUMN_year = "year";
    public static final String COLUMN_rating = "rating";
    public static final String COLUMN_categoryID = "categoryID";

    private static SQLiteDatabase database;

    DatabaseHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = " CREATE TABLE " + TABLE_MOVIES + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_title + " TEXT NOT NULL, " +
                COLUMN_description + " TEXT NOT NULL, " +
                COLUMN_year + " INTEGER NOT NULL, " +
                COLUMN_rating + " INTEGER NOT NULL, " +
                COLUMN_categoryID + " INTEGER NOT NULL);";
        db.execSQL(SQL);
    }

    public static boolean CheckIsDataAlreadyInDBorNot(String TableName, String dbfield, String fieldValue) {
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = database.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MOVIES);
        onCreate(db);
    }
}

