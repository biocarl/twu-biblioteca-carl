package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;

import java.util.ArrayList;
import java.util.HashMap;

public class BookDatabase {
    private static BookDatabase instance = null;
    private HashMap<Integer, Book> bookHashMap;
    private int uID_Counter = 1;

    private BookDatabase() {
        this.bookHashMap = new HashMap<>();
    }

    public int add(Book book) {
        book.setID(uID_Counter);
        uID_Counter++;
        this.bookHashMap.put(book.getID(), book);
        return book.getID();
    }

    public void update(Book book) {
        this.bookHashMap.replace(book.getID(), book);
    }

    public static BookDatabase getInstance() {
        if (instance == null)
            instance = new BookDatabase();
        return instance;
    }

    public ArrayList<Book> getAll() {
        return new ArrayList<Book>(this.bookHashMap.values());
    }

    public void flush() {
        this.bookHashMap.clear();
        this.uID_Counter = 1;
    }

    public Book getById(int id) {
        return this.bookHashMap.get(id);
    }

}