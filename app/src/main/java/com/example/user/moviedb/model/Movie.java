package com.example.user.moviedb.model;

public class Movie {

    private int id;
    private String title;
    private String description;
    private int rating;
    private int year;
    private Category categoryId;

    public Movie(int id, String title, String description, int rating, int year, Category categoryId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.year = year;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }
}
