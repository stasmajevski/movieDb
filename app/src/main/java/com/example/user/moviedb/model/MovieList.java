package com.example.user.moviedb.model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    private List<Movie> movies = new ArrayList<>();

    public MovieList(){
        int i = 1;
        movies.add(
                new Movie(
                 i++,
                "Okja",
                "A young girl risks everything to prevent a powerful, multinational company from kidnapping her best friend - a fascinating beast named Okja.",
                7,
                2017,
                new Category(1,"Adventure")
                )
        );

        movies.add(
                new Movie(
                        i++,
                        "Okja2",
                        "A young girl risks everything to prevent a powerful, multinational company from kidnapping her best friend - a fascinating beast named Okja.",
                        7,
                        2018,
                        new Category(1,"Adventure")
                )
        );

        movies.add(
                new Movie(
                        i++,
                        "Molly's Game",
                        "The true story of Molly Bloom, an Olympic-class skier who ran the world's most exclusive high-stakes poker game and became an FBI target.",
                        8,
                        2017,
                        new Category(2,"Crime")
                )
        );

    }
}
