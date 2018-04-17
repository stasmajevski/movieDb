package com.example.user.moviedb.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.moviedb.R;
import com.example.user.moviedb.model.Category;
import com.example.user.moviedb.model.MovieList;

public class MoviesActivity extends AppCompatActivity {

    TableLayout tableLayout;
    private MovieList  movieList;

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
    }

    private void loadList() {
        for (int i = 0; i < movieList.getMovies().size(); i++) {
            TableRow newEntry = getNewTableRowView(movieList.getMovies().get(i).getTitle(),
                                                   movieList.getMovies().get(i).getYear(),
                                                   movieList.getMovies().get(i).getRating(),
                                                   movieList.getMovies().get(i).getCategoryId());
            newEntry.setId(i);

            final int movieID = i+1;

            newEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MoviesActivity.this,String.valueOf(movieID),Toast.LENGTH_SHORT).show();
                    movieDetails(movieID);
                }
            });

            tableLayout.addView(newEntry);
        }
    }

    private void movieDetails(int movieID) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movieID", movieID);
        startActivity(intent);
    }

    private TableRow getNewTableRowView(String title, int year, int rating, Category categoryID) {

        TableRow rowView = new TableRow(this);

        addDataToRow(rowView,title,String.valueOf(year), String.valueOf(rating), String.valueOf(categoryID.getId()));

        rowView.setPadding(10,10,10,10);

        return rowView;
    }

    private void addDataToRow(TableRow rowView, String... Values) {

        for (String value : Values) {
            TextView textView = new TextView(this);
            textView.setText(value);
            rowView.addView(textView, 200, 150);
        }

    }
}
