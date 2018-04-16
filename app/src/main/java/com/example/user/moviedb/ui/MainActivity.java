package com.example.user.moviedb.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.moviedb.R;

public class MainActivity extends AppCompatActivity {

    private Button enterDbButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterDbButton = (Button)findViewById(R.id.enterDbButton);

        enterDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 enterMovieDb();
            }
        });
    }

    private void enterMovieDb() {
        Intent intent = new Intent(this, MoviesActivity.class);
        startActivity(intent);
    }
}
