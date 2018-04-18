package com.example.user.moviedb;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.user.moviedb.model.MovieList;

public class MovieContentProvider extends ContentProvider {
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.user.moviedb.provider/" + DatabaseHelper.TABLE_MOVIES);
    private Context context;
    private MovieList movieList;
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        context = getContext();
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getReadableDatabase();
        return database != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] columns, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DatabaseHelper.TABLE_MOVIES);
        return builder.query(database, columns, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        long rowId = database.insert(DatabaseHelper.TABLE_MOVIES, null, contentValues);
        if (rowId > -1) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            context.getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLiteException("Insert failed for Uri:" + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
