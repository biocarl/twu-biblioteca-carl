package com.twu.biblioteca;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StartupPrinterTest {

    @Test
    public void printBooks_printsExistingBooks() {
        FakePrintStream fakePrintStream = new FakePrintStream(System.out);
        String[] books = {"Book1","Book2"};
        StartupPrinter startupPrinter = new StartupPrinter(fakePrintStream,books);

        startupPrinter.printBooks();

        assertThat(fakePrintStream.printedString(), is("Book1\nBook2\n"));
    }
}
