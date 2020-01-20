package com.twu.biblioteca;

import java.io.PrintStream;
import java.io.PrintWriter;

public class GreetingPrinter {
    private PrintStream printStream;

    public GreetingPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printGreeting() {
       this.printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!git");
    }
}
