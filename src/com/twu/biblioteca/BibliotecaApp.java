package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        GreetingPrinter greetingPrinter = new GreetingPrinter(System.out);
        greetingPrinter.printGreeting();

        StartupPrinter startupPrinter = new StartupPrinter(System.out, new String[]{""});
        startupPrinter.printBooks();
    }
}
