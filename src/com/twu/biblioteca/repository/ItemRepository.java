package com.twu.biblioteca.repository;

import com.twu.biblioteca.db.ItemDatabase;
import com.twu.biblioteca.domain.Item;

import java.util.ArrayList;

public class ItemRepository {
    public int add(Item item) {
        return ItemDatabase.getInstance().add(item);
    }

    public void update(Item item) {
        ItemDatabase.getInstance().update(item);
    }

    public ArrayList<Item> getAll() {
        return ItemDatabase.getInstance().getAll();
    }

    public void flush() {
        ItemDatabase.getInstance().flush();
    }

    public Item getById(int id) {
        return ItemDatabase.getInstance().getById(id);
    }
}
