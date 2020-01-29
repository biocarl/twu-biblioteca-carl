package com.twu.biblioteca.repository;

import com.twu.biblioteca.db.UserDatabase;
import com.twu.biblioteca.domain.User;

import java.util.ArrayList;

public class UserRepository {
    public int add(User user) {
        return UserDatabase.getInstance().add(user);
    }

    public ArrayList<User> getAll() {
        return UserDatabase.getInstance().getAllUsers();
    }

    public void flush() {
        UserDatabase.getInstance().flush();
    }

    public User getById(int id) {
        return UserDatabase.getInstance().getById(id);
    }
}
