package com.example.user.moviedb.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
    }
}
