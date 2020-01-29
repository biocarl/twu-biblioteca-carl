package com.twu.biblioteca.controller;

import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.security.Session;

import java.util.List;

public class UserController {
    private UserRepository userRepository;
    private User currentUser;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.currentUser = Session.getUser();
    }

    public User getById(int id) {
        return userRepository.getById(id);
    }

    public List<User> getUsers() {
        return userRepository.getAll();
    }
}
