package com.newoneplus.dresshub;

import org.junit.Test;

public class UserTest {


    @Test
    public void get() throws ClassNotFoundException {

        UserDao userDao = new UserDao();
        String id = "admin";
        User user = userDao.get(id);

    }
}
