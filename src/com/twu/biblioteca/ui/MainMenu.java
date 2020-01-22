package com.twu.biblioteca.ui;

import com.twu.biblioteca.BookController;
import com.twu.biblioteca.domain.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class MainMenu {

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;
    private BookController bookController;

    public MainMenu(PrintStream printStream, BufferedReader bufferedReader, BookController bookController) {
        this.printStream = printStream;
        this.bookController = bookController;
        this.bufferedReader = bufferedReader;
    }

    public void printAvailableBooks() {
        for (Book book : bookController.getAvailableBooks()) {
            this.printStream.println(book.toString());
        }
    }

    public void printMenu() {
        printStream.println("1: List of available books");
        printStream.println("2: Check-Out book");
        printStream.println("0: Exit program");
    }

    public void selectOption() throws IOException {
        switch (bufferedReader.readLine()) {
            case "1":
                printAvailableBooks();
                break;
            case "2":
                CheckoutMenu checkoutMenu = new CheckoutMenu(printStream, bufferedReader, bookController);
                checkoutMenu.inflate();
                break;
            case "0":
                exit();
                break;
            default:
                this.printStream.println("Please select a valid option!");
        }
    }

    private void exit() {
        System.exit(0);
    }

    public void inflate() throws IOException {
        printMenu();
        selectOption();
    }
}
