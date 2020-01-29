package com.twu.biblioteca.ui;

import com.twu.biblioteca.controller.ItemController;
import com.twu.biblioteca.controller.UserController;
import com.twu.biblioteca.domain.User;

import java.io.PrintStream;

public class UserListMenu implements Menu {
    private final PrintStream printStream;
    private final ItemController itemController;
    private final UserController userController;

    public UserListMenu(PrintStream printStream, ItemController itemController, UserController userController) {
        this.printStream = printStream;
        this.itemController = itemController;
        this.userController = userController;
    }

    public void inflate() {
        for (User user : userController.getUsers()) {
            final String booklist = itemController.getCheckOutBooksOfUserById(user.getId()).stream().map(e -> e.getTitle() + ",").reduce("", String::concat);
            this.printStream.println(String.format("%s:\t%s", user.getUserName(), booklist));
        }
    }
}
