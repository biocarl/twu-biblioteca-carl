package com.twu.biblioteca.ui;

import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.security.Session;

import java.io.PrintStream;

public class UserProfileMenu implements Menu {
    private PrintStream printStream;
    private User currentUser;

    public UserProfileMenu(PrintStream printStream) {
        this.printStream = printStream;
        this.currentUser = Session.getUser();
    }

    public void inflate() {
        printStream.println(String.format("Personal Information:\nName:%s\nMail:%s\nPhone:%s\n", currentUser.getUserName(), currentUser.getMail(), currentUser.getPhone()));
    }

}
