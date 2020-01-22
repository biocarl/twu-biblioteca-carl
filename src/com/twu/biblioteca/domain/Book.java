package com.twu.biblioteca.domain;

public class Book {
    private String bookTitle;
    private String author;
    private int publicationYear;
    private boolean isCheckout;
    private int id;

    public Book(String bookTitle, String author, int publicationYear) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isCheckout = false;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s", bookTitle, author, publicationYear);
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
}
