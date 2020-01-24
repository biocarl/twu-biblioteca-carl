package com.twu.biblioteca;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GreetingPrinterTest {

    @Test
    public void printGreeting_printsExpectedGreeting() {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        GreetingPrinter greetingPrinter = new GreetingPrinter(spyPrintStream);

        greetingPrinter.printGreeting();

        assertThat(spyPrintStream.printedStrings().get(0), is("Welcome to Biblioteca. Your one-stop-shop for great item titles in Bangalore!"));
    }
}
