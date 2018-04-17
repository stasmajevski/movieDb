package com.example.user.moviedb.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.moviedb.R;
import com.example.user.moviedb.model.MovieList;

public class MovieDetailsActivity extends AppCompatActivity {

    private MovieList movieList;
    public static final String TAG = MovieDetailsActivity.class.getCanonicalName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        int movieID = intent.getIntExtra("movieID",1);

        movieList = new MovieList();

        Log.d(TAG,movieList.getMovies().get(movieID).getDescription());

        TextView movieTitleTextView = (TextView) findViewById(R.id.movieTitleTextView);
        String movieTitleAndYear = movieList.getMovies().get(movieID).getTitle() +
                " (" + String.valueOf(movieList.getMovies().get(movieID).getYear()) + ")";
        movieTitleTextView.setText(movieTitleAndYear);

        TextView movieRatingTextView = (TextView) findViewById(R.id.ratingTextView);
        String movieRating = getString(R.string.rating) + String.valueOf(movieList.getMovies().get(movieID).getRating());
        movieRatingTextView.setText(movieRating);

        TextView movieDescriptionTextView = (TextView) findViewById(R.id.movieDescriptionTextView);
        movieDescriptionTextView.setText(movieList.getMovies().get(movieID).getDescription());

        Button backToListButton = (Button) findViewById(R.id.backToListButton);

        backToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
