package com.twu.biblioteca.ui;

import com.twu.biblioteca.BookController;
import com.twu.biblioteca.domain.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class CheckoutMenu {

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;
    private final BookController bookController;

    public CheckoutMenu(PrintStream printStream, BufferedReader bufferedReader, BookController bookController) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.bookController = bookController;
    }

    public void inflate() throws IOException {
        printBookOptions();
        selectBook();
    }

    public void printBookOptions() {
        int i = 1;
        for (Book book : bookController.getAvailableBooks()) {
            this.printStream.println(i + "\t" + book.toString());
            i++;
        }
    }

    public void selectBook() throws IOException {
        int selection = Integer.parseInt(bufferedReader.readLine()) - 1; // enumeration starts with 1, array with 0
        List<Book> availableBooks = bookController.getAvailableBooks();
        if (selection >= 0 && selection < availableBooks.size() - 1) {
            bookController.checkoutBook(availableBooks.get(selection).getID());
        }
    }
}
