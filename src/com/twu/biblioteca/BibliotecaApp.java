package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Movie;
import com.twu.biblioteca.ui.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException {
        GreetingPrinter greetingPrinter = new GreetingPrinter(System.out);
        greetingPrinter.printGreeting();

        //Default books
        ItemRepository itemRepository = new ItemRepository();
        itemRepository.add(new Book("Demian", "Herman Hesse", 1919));
        itemRepository.add(new Book("Moonwalking with Einstein", "Joshua Foer", 2011));
        itemRepository.add(new Book("Gödel, Escher, Bach: An Eternal Golden Braid", "Douglas Hofstadter\n", 1979));
        //Default movies
        itemRepository.add(new Movie("Mr Robot", 2015, "Sam Esmail", 8));
        itemRepository.add(new Movie("Momo", 1986, "Johannes Schaaf", 4));

        ItemController controller = new ItemController(itemRepository);
        MainMenu mainMenu = new MainMenu(System.out, new BufferedReader(new InputStreamReader(System.in)), controller);

        // Event loop
        while (true) {
            mainMenu.inflate();
        }
    }
}
