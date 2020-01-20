package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;

public class BibliotecaApp {

    public static void main(String[] args) {
        GreetingPrinter greetingPrinter = new GreetingPrinter(System.out);
        greetingPrinter.printGreeting();

        StartupPrinter startupPrinter = new StartupPrinter(System.out, new Book[]{});
        startupPrinter.printBooks();
    }
}
