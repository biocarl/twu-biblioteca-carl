package com.twu.biblioteca.functional;


import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.ItemController;
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
    ItemController itemController;

    @Before
    public void setUp() {
        itemController = new ItemController(getInMemoryDatabase());
    }

    @Test
    public void printAllItems_printsExistingItemsWithDetailsAndFormatted() {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        MainMenu mainMenu = new MainMenu(spyPrintStream, null, itemController);

        mainMenu.printAvailableItems();

        assertThat(spyPrintStream.printedStrings().get(0), is("" + itemController.getById(1)));
        assertThat(spyPrintStream.printedStrings().get(1), is("" + itemController.getById(2)));
    }

    @Test
    public void printMenu_printsAvailableOptions() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);

        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, itemController);
        mainMenu.printMenu();

        assertThat(spyPrintStream.printedStrings().get(0), is("1: List of available books"));
    }

    @Test
    public void selectItem_whenNonExistingOptionRead_printsInvalidOption() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("-1"); //select invalid option
        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, itemController);

        mainMenu.selectItem();

        assertThat(spyPrintStream.printedStrings().get(0), is("Please select a valid option!"));
    }

    @Test
    public void selectItem_whenOption1Read_printsAvailableItems() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1"); //select option 1
        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, itemController);

        mainMenu.selectItem();

        assertThat(spyPrintStream.printedStrings().get(0), is("" + itemController.getById(1)));
        assertThat(spyPrintStream.printedStrings().get(1), is("" + itemController.getById(2)));
    }

}
