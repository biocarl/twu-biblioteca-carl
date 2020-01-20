package com.twu.biblioteca;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GreetingPrinterAppTest {

    @Test
    public void printGreeting_printsExpectedGreeting() {
        FakePrintStream fakePrintStream = new FakePrintStream(System.out);
        GreetingPrinter greetingPrinter = new GreetingPrinter(fakePrintStream);
        greetingPrinter.printGreeting();
        assertThat(fakePrintStream.printedString(), is("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n"));
    }
}
