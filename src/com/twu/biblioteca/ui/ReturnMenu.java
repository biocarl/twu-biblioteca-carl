package com.twu.biblioteca.ui;

import com.twu.biblioteca.BookController;
import com.twu.biblioteca.domain.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ReturnMenu {

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;
    private final BookController bookController;

    public ReturnMenu(PrintStream printStream, BufferedReader bufferedReader, BookController bookController) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.bookController = bookController;
    }

    public void printAllBooks() {
        int i = 1;
        for (Book book : bookController.getAllBooks()) {
            this.printStream.println(i + "\t" + book.toString());
            i++;
        }
    }

    public void inflate() throws IOException {
        final String bookTitle = bufferedReader.readLine();
        this.bookController.returnBook(bookTitle);
    }
}
