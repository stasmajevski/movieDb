package com.example.user.moviedb.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.moviedb.DatabaseHelper;

import com.example.user.moviedb.MovieContentProvider;
import com.example.user.moviedb.R;
import com.example.user.moviedb.model.Category;
import com.example.user.moviedb.model.MovieList;

public class MoviesActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private MovieList  movieList;
    public static final String TAG = MoviesActivity.class.getCanonicalName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        movieList = new MovieList();
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        TableRow headerRow = new TableRow(this);

        addDataToRow(headerRow,"Title","Year","Rating", "Category ID");

        tableLayout.addView(headerRow);

        loadList();

        updateList();
    }


    private void loadList(){
        for (int i = 0; i < movieList.getMovies().size(); i++) {

            if(!DatabaseHelper.CheckIsDataAlreadyInDBorNot(DatabaseHelper.TABLE_MOVIES,DatabaseHelper.COLUMN_ID,
                    String.valueOf(movieList.getMovies().get(i).getId()))) {

                addContact(movieList.getMovies().get(i).getId(), movieList.getMovies().get(i).getTitle(),
                        movieList.getMovies().get(i).getDescription(),
                        movieList.getMovies().get(i).getYear(),
                        movieList.getMovies().get(i).getRating(),
                        movieList.getMovies().get(i).getCategoryId());
            }
        }
    }

    public void updateList() {

        Cursor cursor = getContentResolver().query(MovieContentProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_title));
                int year = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_year));
                int rating = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_rating));
                int categoryID = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_categoryID));

                TableRow tableRow = getNewTableRowView(title,year,rating,categoryID);
                tableRow.setId(id);

                final int movieID = --id;

                tableRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MoviesActivity.this,String.valueOf(movieID),Toast.LENGTH_SHORT).show();
                        movieDetails(movieID);
                    }
                });

                tableLayout.addView(tableRow);
            } while(cursor.moveToNext());
            cursor.close();
        }
    }
    private void addContact(int ID, String title,String description,int year, int rating, Category categoryID) {

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_ID, ID);
        values.put(DatabaseHelper.COLUMN_title, title);
        values.put(DatabaseHelper.COLUMN_description, description);
        values.put(DatabaseHelper.COLUMN_year, year);
        values.put(DatabaseHelper.COLUMN_rating, rating);
        values.put(DatabaseHelper.COLUMN_categoryID, categoryID.getId());
        Uri uri = getContentResolver().insert(MovieContentProvider.CONTENT_URI, values);

    }

    private TableRow getNewTableRowView(String title, int year, int rating, int categoryID) {

        TableRow rowView = new TableRow(this);

        addDataToRow(rowView,title,String.valueOf(year), String.valueOf(rating), String.valueOf(categoryID));

        rowView.setPadding(10,10,10,10);

        return rowView;
    }

    private void addDataToRow(TableRow rowView, String... Values) {

        for (String value : Values) {
            TextView textView = new TextView(this);
            textView.setText(value);
            rowView.addView(textView, 310, 150);
        }
    }

    private void movieDetails(int movieID) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movieID", movieID);
        startActivity(intent);
    }
}
