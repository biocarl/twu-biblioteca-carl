package com.twu.biblioteca.domain;

public class Movie implements Item {
    String title;
    int year;
    String director;
    int rating;

    boolean isCheckout;
    private int id;
    private final String type;

    public Movie(String title, int year, String director, int rating) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.type = "movie";
    }


    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s", title, year, director, rating);
    }

    @Override
    public void setIsCheckout(boolean isCheckout) {
        this.isCheckout = isCheckout;
    }

    @Override
    public boolean isCheckout() {
        return isCheckout;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
