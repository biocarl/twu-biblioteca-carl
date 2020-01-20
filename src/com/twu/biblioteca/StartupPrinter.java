package com.twu.biblioteca;

import java.io.PrintStream;

public class StartupPrinter {

    private final PrintStream printStream;
    private String[] books;

    public StartupPrinter(PrintStream printStream, String[] books) {
        this.printStream = printStream;
        this.books = books;
    }

    public void printBooks() {
        for(String book : books){
            this.printStream.println(book);
        }
    }
}
