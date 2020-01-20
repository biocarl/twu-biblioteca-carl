package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

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
    }

    public void selectOption() throws IOException {
        if(bufferedReader.readLine().equals("1")){
            printBooks();
        }else{
            this.printStream.println("Please select a valid option!");
        }
    }
}
