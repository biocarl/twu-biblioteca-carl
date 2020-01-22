package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.getAll().stream().filter(book -> !book.isCheckout()).collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    public boolean checkoutBook(int id) {
        Book book = bookRepository.getById(id);
        if(! book.isCheckout()) {
            book.setIsCheckout(true);
            bookRepository.update(book);
            return true;
        }
        return false;
    }

    public Book getById(int id) {
        return bookRepository.getById(id);
    }

    public boolean returnBook(String bookName) {
        Optional<Book> book = bookRepository.getAll().stream().filter(b -> b.getTitle().equals(bookName)).findFirst();
        if(book.isPresent() && book.get().isCheckout()){
            Book b = book.get();
            b.setIsCheckout(false);
            bookRepository.update(b);
            return true;
        }
        return false;
    }
}
