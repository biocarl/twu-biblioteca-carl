package com.twu.biblioteca;


import com.twu.biblioteca.domain.Book;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandInterfaceTest {
    //Helpers
    public Book[] getBooks(){
        return new Book[] {new Book("Name1","Author",1999), new Book("Name2","Author2",1992)};
    }

    @Test
    public void printBooks_printsExistingBooksWithDetailsAndFormatted() {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        Book[] books = getBooks();
        CommandInterface commandInterface = new CommandInterface(spyPrintStream, null, books);

        commandInterface.printBooks();

        assertThat(spyPrintStream.printedStrings().get(0), is(""+books[0]));
        assertThat(spyPrintStream.printedStrings().get(1), is(""+books[1]));
    }


    @Test
    public void initMenu_whenNonExistingOptionRead_printsMenu() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        Book[] books = getBooks();
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("-1"); //select invalid option
        CommandInterface commandInterface = new CommandInterface(spyPrintStream, bufferedReader, books);

        commandInterface.initMenu();

        assertThat(spyPrintStream.printedStrings().get(0), is("1: List of books"));
    }

    @Test
    public void initMenu_whenOption1Read_printsAvailableBooks() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        Book[] books = getBooks();
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1"); //select option 1
        CommandInterface commandInterface = new CommandInterface(spyPrintStream, bufferedReader, books);

        commandInterface.initMenu();

        assertThat(spyPrintStream.printedStrings().get(0), is("1: List of books"));
        assertThat(spyPrintStream.printedStrings().get(1), is(""+books[0]));
        assertThat(spyPrintStream.printedStrings().get(2), is(""+books[1]));
    }

}
