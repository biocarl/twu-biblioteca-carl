package com.twu.biblioteca.db;

import com.twu.biblioteca.domain.User;

import java.util.ArrayList;

public class UserDatabase {
    private static UserDatabase instance = null;
    private ArrayList<User> users;

    private int uID_Counter = 1;

    private UserDatabase() {
        this.users = new ArrayList<>();
    }

    public int add(User user) {
        user.setId(uID_Counter);
        uID_Counter++;
        this.users.add(user);
        return user.getId();
    }

    public static UserDatabase getInstance() {
        if (instance == null)
            instance = new UserDatabase();
        return instance;
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<User>(this.users);
    }

    public void flush() {
        this.users.clear();
        this.uID_Counter = 1;
    }

    public User getById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().get();
    }

}