package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class TestHelper {
    public static ItemRepository getInMemoryDatabase() {
        ItemRepository itemRepository = new ItemRepository();
        itemRepository.flush();
        itemRepository.add(getItems().get(0));
        itemRepository.add(getItems().get(1));
        return itemRepository;
    }

    public static ArrayList<Item> getItems() {
        return new ArrayList<Item>(Arrays.asList(
                new Book("Name1", "Author", 1990),
                new Book("Name2", "Author2", 1992),
                new Book("Name3", "Author3", 1991),
                new Book("Name4", "Author4", 1999)
        ));
    }
}
