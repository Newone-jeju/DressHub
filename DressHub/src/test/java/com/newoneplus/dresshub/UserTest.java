package com.newoneplus.dresshub;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {
    private UserDao userDao;
    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
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
    public void insert() throws SQLException, ClassNotFoundException, ParseException {
        userDao.deleteAll();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        User user = new User();
        user.setId("user1");
        user.setPassword("0405");
        user.setName("ming");
        user.setEmail("hello");
        user.setAddress("new");
        user.setPhoneNumber("01012341234");
        user.setNickname("ming");
        user.setIntroduce("zzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        user.setOpenPrivateInfo(true);
        user.setCertification(true);
        user.setResisterDate(date);
        userDao.insert(user);
        User insertedUser = userDao.get(user.getId());
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
        assertThat(insertedUser.getEmail(), is(user.getEmail()));
        assertThat(insertedUser.getAddress(), is(user.getAddress()));
        assertThat(insertedUser.getPhoneNumber(), is(user.getPhoneNumber()));
        assertThat(insertedUser.getNickname(), is(user.getNickname()));
        assertThat(insertedUser.getIntroduce(), is(user.getIntroduce()));
        assertThat(insertedUser.isOpenPrivateInfo(), is(user.isOpenPrivateInfo()));
        assertThat(insertedUser.isCertification(), is(user.isCertification()));
        assertThat(insertedUser.getResisterDate(), is(user.getResisterDate()));

    }



//
    @Test
    public void loginCheck(){
        User user = new User();
        String id = "user1";
        String password = "0405";
        boolean loginCheckUser = userDao.loginCheck(id, password);
        assertThat(true, is(loginCheckUser));
    }


}
