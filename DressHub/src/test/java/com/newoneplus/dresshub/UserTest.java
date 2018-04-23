package com.newoneplus.dresshub;

import org.apache.tomcat.jni.Time;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserTest {
    private UserDao userDao;

//    @Autowired
    private User user;
    @Before
    public void setup() {
        userDao = new UserDao();
    }


    @Test
    public void get() throws ClassNotFoundException, SQLException {

        String id = "user1";
        String password = "0405";

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getPassword(), is(password));

    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
//        Date date = new Date(0);
        // 전부 삭제
        userDao.deleteAll();
        User user = new User();
        user.setId("user1");
        user.setPassword("0405");
        user.setName("ming");
        user.setEmail("email");
        user.setAddress("jejudo");
        user.setPhoneNumber("01012341234");
        user.setNickname("ming");
        user.setIntroduce("zzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        user.setOpenPrivateInfo(true);
        user.setCertification(true);
//        user.setResisterDate(date);

        userDao.insert(user);
        User insertedUser = userDao.get(user.getId());
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
//        assertThat(insertedUser.getEmail(), is(user.getEmail()));
//        assertThat(insertedUser.getAddress(), is(user.getAddress()));
//        assertThat(insertedUser.getPhoneNumber(), is(user.getPhoneNumber()));
//        assertThat(insertedUser.getNickname(), is(user.getNickname()));
//        assertThat(insertedUser.getIntroduce(), is(user.getIntroduce()));
//        assertThat(insertedUser.isOpenPrivateInfo(), is(user.isOpenPrivateInfo()));
//        assertThat(insertedUser.isCertification(), is(user.isCertification()));
//        assertThat(insertedUser.getResisterDate(), is(user.getResisterDate()));
    }

}
