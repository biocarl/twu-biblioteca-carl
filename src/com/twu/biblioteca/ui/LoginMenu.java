package com.twu.biblioteca.ui;

import com.twu.biblioteca.security.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class LoginMenu implements Menu {
    private PrintStream printStream;
    private BufferedReader bufferedReader;

    public LoginMenu(PrintStream printStream, BufferedReader bufferedReader) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
    }

    public void printGreeting() {
        this.printStream.println("Welcome to Biblioteca. Your one-stop-shop for great item titles in Bangalore!");
    }

    public boolean parseCredentials() throws IOException {
        String userName = bufferedReader.readLine();
        String password = bufferedReader.readLine();
        Session.initSession(userName, password);
        if (Session.isValid()) {
            this.printStream.println("Login successful");
            return true;
        } else {
            this.printStream.println("Try again");
            return false;
        }
    }

    @Override
    public void inflate() throws IOException {
        printGreeting();
        parseCredentials();
    }
}
