package com.twu.biblioteca;

import com.twu.biblioteca.domain.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemDatabase {
    private static ItemDatabase instance = null;
    private HashMap<Integer, Item> itemHashMap;
    private int uID_Counter = 1;

    private ItemDatabase() {
        this.itemHashMap = new HashMap<>();
    }

    public int add(Item item) {
        item.setID(uID_Counter);
        uID_Counter++;
        this.itemHashMap.put(item.getID(), item);
        return item.getID();
    }

    public void update(Item item) {
        this.itemHashMap.replace(item.getID(), item);
    }

    public static ItemDatabase getInstance() {
        if (instance == null)
            instance = new ItemDatabase();
        return instance;
    }

    public ArrayList<Item> getAll() {
        return new ArrayList<Item>(this.itemHashMap.values());
    }

    public void flush() {
        this.itemHashMap.clear();
        this.uID_Counter = 1;
    }

    public Item getById(int id) {
        return this.itemHashMap.get(id);
    }

}