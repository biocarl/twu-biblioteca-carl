package com.twu.biblioteca.domain;

public class Book {
    private String bookTitle;
    private String author;
    private int publicationYear;

    public Book(String bookTitle, String author, int publicationYear) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
    }


    @Override
    public String toString(){
        return String.format("%s\t%s\t%s",bookTitle,author,publicationYear);
    }
}
