package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException {
        GreetingPrinter greetingPrinter = new GreetingPrinter(System.out);
        greetingPrinter.printGreeting();

        //Default books
        Book[] books = {new Book("Demian", "Herman Hesse",1982)};


        CommandInterface commandInterface = new CommandInterface(System.out, new BufferedReader(new InputStreamReader( System.in)), books);
        commandInterface.initMenu();
    }
}
