package com.twu.biblioteca.domain;

public class Book implements Item {
    private String title;
    private String author;
    private int publicationYear;
    private boolean isCheckout;
    private int id;
    private final String type;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isCheckout = false;
        this.type = "book";
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s", title, author, publicationYear);
    }

    public void setIsCheckout(boolean isCheckout) {
        this.isCheckout = isCheckout;
    }

    public boolean isCheckout() {
        return this.isCheckout;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getType(){
        return this.type;
    }

}
