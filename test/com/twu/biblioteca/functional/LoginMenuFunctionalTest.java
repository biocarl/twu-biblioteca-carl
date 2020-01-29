package com.twu.biblioteca.functional;


import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.ui.LoginMenu;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginMenuFunctionalTest {

    @Test
    public void printGreeting_printsExpectedGreeting() {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        LoginMenu loginMenu = new LoginMenu(spyPrintStream, null);

        loginMenu.printGreeting();

        assertThat(spyPrintStream.printedStrings().get(0), is("Welcome to Biblioteca. Your one-stop-shop for great item titles in Bangalore!"));
    }
}
