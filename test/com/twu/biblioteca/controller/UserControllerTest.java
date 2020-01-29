package com.twu.biblioteca.controller;

import com.twu.biblioteca.domain.Item;
import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.TestHelper.getBook;
import static com.twu.biblioteca.TestHelper.getMovie;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserControllerTest {

    @Before
    public void setUp() {

    }

    @Test
    public void getById_returnsCorrectUserInstance() {
        UserRepository repository = new UserRepository();
        int id = repository.add(new User("Test1", "password1", true));

        UserController controller = new UserController(new UserRepository());
        User user = controller.getById(id);
        assertThat(true, is(user.getId() == id));
    }

    @Test
    public void getAvailableBooks_returnsOnlyAssociatedBooks() {
        UserRepository repository = new UserRepository();
        User user = new User("Test1", "password1", true);
        Item book = getBook();
        Item movie = getMovie();
//        user.addItem(book);
//        user.addItem(movie);
        repository.add(user);
        UserController controller = new UserController(repository);
        // TODO where you where
    }

}
