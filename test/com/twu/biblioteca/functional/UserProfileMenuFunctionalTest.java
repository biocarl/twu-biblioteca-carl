package com.twu.biblioteca.functional;

import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.controller.UserController;
import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.security.Session;
import com.twu.biblioteca.ui.UserProfileMenu;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.TestHelper.getInMemoryUserDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserProfileMenuFunctionalTest {

    @Before
    public void setUp() {
        Session.logout();
    }

    @Test
    public void inflate_onlyCurrentUserInformationPrinted() {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        UserRepository userRepository = getInMemoryUserDatabase();
        UserController userController = new UserController(userRepository);
        Session.setup(userRepository);
        Session.initSession("Carl", "password1");

        User user = Session.getUser();

        UserProfileMenu userProfileMenu = new UserProfileMenu(spyPrintStream);
        userProfileMenu.inflate();

        assertThat(spyPrintStream.printedStrings().get(0), is(
                String.format("Personal Information:\nName:%s\nMail:%s\nPhone:%s\n", user.getUserName(), user.getMail(), user.getPhone())
        ));
    }
}
