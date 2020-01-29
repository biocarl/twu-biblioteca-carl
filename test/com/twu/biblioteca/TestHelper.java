package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Item;
import com.twu.biblioteca.domain.Movie;
import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.repository.ItemRepository;
import com.twu.biblioteca.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class TestHelper {
    public static ItemRepository getInMemoryItemDatabase() {
        ItemRepository itemRepository = new ItemRepository();
        itemRepository.flush();
        itemRepository.add(getItems().get(0));
        itemRepository.add(getItems().get(1));
        return itemRepository;
    }

    public static UserRepository getInMemoryUserDatabase() {
        UserRepository userRepository = new UserRepository();
        userRepository.flush();
        userRepository.add(getUsers().get(0));
        userRepository.add(getUsers().get(1));
        return userRepository;
    }

    public static ArrayList<Item> getItems() {
        ArrayList<Item> list = new ArrayList<Item>(Arrays.asList(
                new Book("Name1", "Author", 1990),
                new Book("Name2", "Author2", 1992),
                new Book("Name3", "Author3", 1991),
                new Movie("Name3", 1991, "director1", 1),
                new Movie("Name1", 1992, "director2", 10),
                new Book("Name4", "Author4", 1999)
        ));
        //Assigning unique ids
        for (int i = 1; i <= list.size(); i++) {
            list.get(i - 1).setId(i);
        }
        return list;
    }

    public static Item getBook() {
        return getItems().stream().filter(b -> b.getType().equals("book")).findFirst().get();
    }

    public static Item getMovie() {
        return getItems().stream().filter(b -> b.getType().equals("movie")).findFirst().get();
    }

    public static ArrayList<User> getUsers() {
        return new ArrayList<User>(Arrays.asList(
                new User("Carl", "password1", true),
                new User("Lisa", "password2", false)
        ));
    }
}
