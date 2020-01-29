package com.twu.biblioteca;

import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.security.Session;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.TestHelper.getInMemoryUserDatabase;
import static com.twu.biblioteca.TestHelper.getUsers;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SessionTest {

    @Before
    public void setup() {
        Session.logout();
    }

    @Test
    public void initSession_whenProvidingValidCredentials_returnsValidSession() {
        Session.setup(getInMemoryUserDatabase());
        User user = getUsers().get(0);
        Session session = Session.initSession(user.getUserName(), user.getPassword());
        assertThat(true, is(Session.isValid()));
        assertThat(true, is(Session.getUser().getId() != 0));
    }

    @Test
    public void initSession_whenProvidingInValidCredentials_returnsValidSession() {
        Session.setup(getInMemoryUserDatabase());
        User user = getUsers().get(0);
        Session session = Session.initSession(user.getUserName(), user.getPassword() + "ABC");
        assertThat(false, is(Session.isValid()));
    }


}
