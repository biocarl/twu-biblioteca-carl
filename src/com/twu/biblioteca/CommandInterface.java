package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandInterface {

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;
    private Book [] books;

    public CommandInterface(PrintStream printStream, BufferedReader bufferedReader, Book[] books) {
        this.printStream = printStream;
        this.books = books;
        this.bufferedReader = bufferedReader;
    }

    public void printBooks() {
        for(Book book : books){
            this.printStream.println(book.toString());
        }
    }

    public void printMenu() {
        printStream.println("1: List of books");
        printStream.println("0: Exit program");
    }

    public void selectOption() throws IOException {
        switch (bufferedReader.readLine()){
            case "1":
                printBooks();
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
}
