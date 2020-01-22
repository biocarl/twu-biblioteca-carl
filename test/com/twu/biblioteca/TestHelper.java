package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;

import java.util.ArrayList;
import java.util.Arrays;

public class TestHelper {
    public static BookRepository getInMemoryDatabase() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.flush();
        bookRepository.add(getBooks().get(0));
        bookRepository.add(getBooks().get(1));
        return bookRepository;
    }

    public static ArrayList<Book> getBooks() {
        return new ArrayList<>(Arrays.asList(
                new Book("Name1", "Author", 1990),
                new Book("Name2", "Author2", 1992),
                new Book("Name3", "Author3", 1991),
                new Book("Name4", "Author4", 1999)
        ));
    }
}
