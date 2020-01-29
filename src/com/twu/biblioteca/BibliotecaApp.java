package com.twu.biblioteca;

import com.twu.biblioteca.controller.ItemController;
import com.twu.biblioteca.controller.UserController;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Movie;
import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.repository.ItemRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.security.Session;
import com.twu.biblioteca.ui.LoginMenu;
import com.twu.biblioteca.ui.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final PrintStream out = System.out;

        // Setting up dummy users
        UserRepository userRepository = new UserRepository();
        userRepository.add(new User("Carl", "password1", true));
        userRepository.add(new User("Lisa", "password2", false));
        UserController userController = new UserController(userRepository);
        Session.setup(userRepository);

        while (!Session.isValid()) {
            LoginMenu loginMenu = new LoginMenu(out, in);
            loginMenu.inflate();
        }

        //Setting up dummy items
        ItemRepository itemRepository = new ItemRepository();
        itemRepository.add(new Book("Demian", "Herman Hesse", 1919));
        itemRepository.add(new Book("Moonwalking with Einstein", "Joshua Foer", 2011));
        itemRepository.add(new Book("Gödel, Escher, Bach: An Eternal Golden Braid", "Douglas Hofstadter\n", 1979));
        itemRepository.add(new Movie("Mr Robot", 2015, "Sam Esmail", 8));
        itemRepository.add(new Movie("Momo", 1986, "Johannes Schaaf", 4));

        ItemController itemController = new ItemController(itemRepository);
        MainMenu mainMenu = new MainMenu(out, in, itemController, userController);

        // Event loop
        while (true) {
            mainMenu.inflate();
        }
    }
}
