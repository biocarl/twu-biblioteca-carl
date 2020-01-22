package com.twu.biblioteca.functional;


import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.BookController;
import com.twu.biblioteca.ui.MainMenu;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static com.twu.biblioteca.TestHelper.getInMemoryDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainMenuFunctionalTest {
    BookController bookController;

    @Before
    public void setUp() {
        bookController = new BookController(getInMemoryDatabase());
    }

    @Test
    public void printBooks_printsExistingBooksWithDetailsAndFormatted() {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        MainMenu mainMenu = new MainMenu(spyPrintStream, null, bookController);

        mainMenu.printAvailableBooks();

        assertThat(spyPrintStream.printedStrings().get(0), is("" + bookController.getById(1)));
        assertThat(spyPrintStream.printedStrings().get(1), is("" + bookController.getById(2)));
    }

    @Test
    public void printMenu_printsAvailableOptions() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);

        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, bookController);
        mainMenu.printMenu();

        assertThat(spyPrintStream.printedStrings().get(0), is("1: List of available books"));
    }

    @Test
    public void selectOption_whenNonExistingOptionRead_printsInvalidOption() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("-1"); //select invalid option
        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, bookController);

        mainMenu.selectOption();

        assertThat(spyPrintStream.printedStrings().get(0), is("Please select a valid option!"));
    }

    @Test
    public void selectOption_whenOption1Read_printsAvailableBooks() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1"); //select option 1
        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, bookController);

        mainMenu.selectOption();

        assertThat(spyPrintStream.printedStrings().get(0), is("" + bookController.getById(1)));
        assertThat(spyPrintStream.printedStrings().get(1), is("" + bookController.getById(2)));
    }

}
