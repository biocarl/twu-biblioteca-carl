package com.twu.biblioteca.functional;


import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.controller.ItemController;
import com.twu.biblioteca.controller.UserController;
import com.twu.biblioteca.security.Session;
import com.twu.biblioteca.ui.MainMenu;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static com.twu.biblioteca.TestHelper.getInMemoryItemDatabase;
import static com.twu.biblioteca.TestHelper.getInMemoryUserDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainMenuFunctionalTest {
    ItemController itemController;
    UserController userController;

    @Before
    public void setUp() {
        itemController = new ItemController(getInMemoryItemDatabase());
        userController = mock(UserController.class);
        Session.setup(getInMemoryUserDatabase());

        Session.initSession("Carl", "password1");
    }

    @Test
    public void printMenu_printsAvailableOptions() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);

        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, itemController, userController);
        mainMenu.printMenu();

        assertThat(spyPrintStream.printedStrings().get(0), is("1: List all available books"));
    }

    @Test
    public void printMenu_whenLoggedAsAdmin_printsViewUsersOptions() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        Session.initSession("Carl", "password1");

        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, itemController, userController);
        mainMenu.printMenu();

        final boolean containsAdminOption = spyPrintStream.printedStrings().stream().anyMatch(e -> e.contains("[Admin] View books checked out"));
        assertThat(containsAdminOption, is(true));
    }

    @Test
    public void printMenu_whenLoggedAsUser_printsNoViewUsersOptionsButUserProfile() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        Session.initSession("Lisa", "password2");

        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, itemController, userController);
        mainMenu.printMenu();

        final boolean containsProfileInformation = spyPrintStream.printedStrings().stream().anyMatch(e -> e.contains("7: View profile"));
        assertThat(containsProfileInformation, is(true));
    }

    @Test
    public void selectItem_whenNonExistingOptionRead_printsInvalidOption() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("-1"); //select invalid option
        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, itemController, userController);

        mainMenu.selectItem();

        assertThat(spyPrintStream.printedStrings().get(0), is("Please select a valid option!"));
    }

    @Test
    public void selectItem_whenOption1Read_printsAvailableItems() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1"); //select option 1
        MainMenu mainMenu = new MainMenu(spyPrintStream, bufferedReader, itemController, userController);

        mainMenu.selectItem();

        assertThat(spyPrintStream.printedStrings().get(0), is("" + itemController.getById(1)));
        assertThat(spyPrintStream.printedStrings().get(1), is("" + itemController.getById(2)));
    }
}
