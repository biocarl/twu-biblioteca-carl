package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;

import java.io.PrintStream;

public class StartupPrinter {

    private final PrintStream printStream;
    private Book [] books;

    public StartupPrinter(PrintStream printStream, Book[] books) {
        this.printStream = printStream;
        this.books = books;
    }

    public void printBooks() {
        for(Book book : books){
            this.printStream.println(book.toString());
        }
    }
}
