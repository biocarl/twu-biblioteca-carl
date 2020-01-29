package com.twu.biblioteca.security;

import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.repository.UserRepository;

import java.util.Optional;

public class Session {
    private static Session instance = null;
    private static User currentUser;
    private static UserRepository userRepository;

    private Session() {
    }

    public static void setup(UserRepository repository) {
        userRepository = repository;
    }

    public static Session initSession(String userName, String password) {
        Optional<User> user = userRepository.getAll().stream().filter(u -> u.getUserName().equals(userName) && u.getPassword().equals(password)).findFirst();
        if (user.isPresent()) {
            currentUser = user.get();
            if (instance == null) instance = new Session();
            return instance;
        } else {
            //TODO throw exception
            return null;
        }
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isValid() {
        return currentUser != null;
    }

    public static User getUser() {
        return currentUser;
    }
}
