package com.twu.biblioteca.functional;

import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.controller.ItemController;
import com.twu.biblioteca.controller.UserController;
import com.twu.biblioteca.domain.Item;
import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.security.Session;
import com.twu.biblioteca.ui.UserListMenu;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.twu.biblioteca.TestHelper.getInMemoryItemDatabase;
import static com.twu.biblioteca.TestHelper.getInMemoryUserDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserListMenuFunctionalTest {
    ItemController itemController;

    @Before
    public void setUp() {
        itemController = new ItemController(getInMemoryItemDatabase());
        Session.logout();
    }

    @Test
    public void printAllUsers_whenCheckedOutItems() {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        List<Item> items = itemController.getAvailableItemsOfType();
        Item item1 = items.get(0);
        Item item2 = items.get(1);
        UserRepository userRepository = getInMemoryUserDatabase();
        UserController userController = new UserController(userRepository);
        Session.setup(userRepository);
        Session.initSession("Carl", "password1");
        User user = Session.getUser();
        itemController.checkoutItem(item1.getId());
        itemController.checkoutItem(item2.getId());

        String booklist = itemController.getCheckOutBooksOfUserById(user.getId()).stream().map(e -> e.getTitle() + ",").reduce("", String::concat);
        UserListMenu menu = new UserListMenu(spyPrintStream, itemController, userController);
        menu.inflate();

        assertThat(spyPrintStream.printedStrings().get(0), is(String.format("%s:\t%s", user.getUserName(), booklist)));
    }
}
