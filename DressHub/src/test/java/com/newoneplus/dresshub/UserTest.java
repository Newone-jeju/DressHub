package com.newoneplus.dresshub;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserTest {
    private UserDao userDao;
    @Before
    public void setup() throws ClassNotFoundException {
        userDao = new UserDao();
    }


    @Test
    public void get() throws ClassNotFoundException, SQLException {

        String id = "admin";
        String password = "1234";

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getPassword(), is(password));

    }
}
