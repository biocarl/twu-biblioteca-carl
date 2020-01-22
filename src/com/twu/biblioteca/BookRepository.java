package com.twu.biblioteca;

import com.twu.biblioteca.BookDatabase;
import com.twu.biblioteca.domain.Book;

import java.util.ArrayList;

public class BookRepository {
    public int add(Book book) {
        return BookDatabase.getInstance().add(book);
    }

    public void update(Book book) {
        BookDatabase.getInstance().update(book);
    }

    public ArrayList<Book> getAll() {
        return BookDatabase.getInstance().getAll();
    }

    public void flush() {
        BookDatabase.getInstance().flush();
    }

    public Book getById(int id) {
        return BookDatabase.getInstance().getById(id);
    }
}
