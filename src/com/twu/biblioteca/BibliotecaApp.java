package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.ui.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException {
        GreetingPrinter greetingPrinter = new GreetingPrinter(System.out);
        greetingPrinter.printGreeting();

        //Default books
        BookRepository bookRepository = new BookRepository();
        bookRepository.add(new Book("Demian", "Herman Hesse", 1919));
        bookRepository.add(new Book("Moonwalking with Einstein", "Joshua Foer", 2011));
        bookRepository.add(new Book("Gödel, Escher, Bach: An Eternal Golden Braid", "Douglas Hofstadter\n", 1979));

        BookController controller = new BookController(bookRepository);
        MainMenu mainMenu = new MainMenu(System.out, new BufferedReader(new InputStreamReader(System.in)), controller);

        // Event loop
        while (true) {
            mainMenu.inflate();
        }
    }
}
