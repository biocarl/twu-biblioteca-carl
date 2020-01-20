package com.twu.biblioteca;


import com.twu.biblioteca.domain.Book;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StartupPrinterTest {

    @Test
    public void printBooks_printsExistingBooksWithDetailsAndFormatted() {
        FakePrintStream fakePrintStream = new FakePrintStream(System.out);
        Book[] books = new Book[] {new Book("Name1","Author",1999), new Book("Name2","Author2",1992)};

        StartupPrinter startupPrinter = new StartupPrinter(fakePrintStream,books);

        startupPrinter.printBooks();

        assertThat(fakePrintStream.printedString(), is(String.format("%s\n%s\n",books[0],books[1])));
    }

}
