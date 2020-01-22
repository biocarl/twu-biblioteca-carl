package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.getAll().stream().filter(book -> !book.isCheckout()).collect(Collectors.toList());
    }

    public void checkoutBook(int id) {
        Book book = bookRepository.getById(id);
        book.setIsCheckout(true);
        bookRepository.update(book);
    }

    public Book getById(int id) {
        return bookRepository.getById(id);
    }

}
