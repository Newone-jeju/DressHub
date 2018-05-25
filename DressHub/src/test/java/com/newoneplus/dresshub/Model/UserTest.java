//package com.newoneplus.dresshub.Model;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//public class UserTest {
//    private UserDao userDao;
//    @Before
//    public void setup() {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//        userDao = applicationContext.getBean("userDao", UserDao.class);
//    }
//
//
//    @Test
//    public void get() throws ClassNotFoundException, SQLException {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        bCryptPasswordEncoder.
//        String id = "user13456";
//        String password = "0405";
//        User user = userDao.get(id);
//        assertThat(user.getId(), is(id));
//        assertThat(bCryptPasswordEncoder.matches(password, user.getPassword()), is(true));
//
//    }
//
////    @Test
////    public void insert() throws SQLException, ClassNotFoundException, ParseException {
//////        userDao.deleteAll();
////
////        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
////        Date date = null;
////        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
////        User user = new User();
////        user.setId("user1dddaaaaa111");
////        insertUserTest(bCryptPasswordEncoder, date, user);
////        User insertedUser = userDao.get(user.getId());
////        assertThat(insertedUser.getId(), is(user.getId()));
////        assertThat(insertedUser.getName(), is(user.getName()));
////        assertThat(insertedUser.getPassword(), is(user.getPassword()));
////        assertThat(insertedUser.getEmail(), is(user.getEmail()));
////        assertThat(insertedUser.getAddress(), is(user.getAddress()));
////        assertThat(insertedUser.getPhoneNumber(), is(user.getPhoneNumber()));
////        assertThat(insertedUser.getNickname(), is(user.getNickname()));
////        assertThat(insertedUser.getIntroduce(), is(user.getIntroduce()));
////        assertThat(insertedUser.isOpenPrivateInfo(), is(user.isOpenPrivateInfo()));
////        assertThat(insertedUser.isCertification(), is(user.isCertification()));
////        assertThat(insertedUser.getResisterDate(), is(user.getResisterDate()));
//
////    }
//
//    private String insertUserTest(BCryptPasswordEncoder bCryptPasswordEncoder, Date date, User user) throws ClassNotFoundException {
//        user.setId("user239423423");
//        user.setPassword(bCryptPasswordEncoder.encode("0405"));
//        user.setName("ming");
//        user.setEmail("hello");
//        user.setAddress("new");
//        user.setPhoneNumber("01012341234");
//        user.setNickname("ming");
//        user.setIntroduce("zzzzzzzzzzzzzzzzzzzzzzzzzzzz");
//        user.setOpenPrivateInfo(true);
//        user.setCertification(true);
//        user.setResisterDate(date);
//        return userDao.insert(user);
//    }
//
//
//    // 로그인 테스트
//    @Test
//    public void loginCheck(){
////        User user = new User();
//        String id = "user12323";
//        String password = "0405";
//        boolean loginCheckUser = userDao.isValidUser(id, password);
//        assertThat(true, is(loginCheckUser));
//    }
//    // 유저 정보 변경 테스트
//    @Test
//    public void update() throws ParseException, ClassNotFoundException, SQLException {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
//        User user = new User();
//
//        insertUserTest(bCryptPasswordEncoder, date, user);
//        user.setPassword(bCryptPasswordEncoder.encode("0407"));
//        user.setName("ming12345");
//        user.setEmail("hello");
//        user.setAddress("new");
//        user.setPhoneNumber("01012341234");
//        user.setNickname("ming");
//        user.setIntroduce("zzzzzzzzzzzzzzzzzzzzzzzzzzzz");
//        user.setOpenPrivateInfo(true);
//        user.setCertification(true);
//        userDao.update(user);
//
//        User updatedUser = userDao.get(user.getId());
//        assertThat(updatedUser.getId(), is(user.getId()));
//        assertThat(updatedUser.getName(), is(user.getName()));
//        assertThat(updatedUser.getPassword(), is(user.getPassword()));
//        assertThat(updatedUser.getEmail(), is(user.getEmail()));
//        assertThat(updatedUser.getAddress(), is(user.getAddress()));
//        assertThat(updatedUser.getPhoneNumber(), is(user.getPhoneNumber()));
//        assertThat(updatedUser.getNickname(), is(user.getNickname()));
//        assertThat(updatedUser.getIntroduce(), is(user.getIntroduce()));
//        assertThat(updatedUser.isOpenPrivateInfo(), is(user.isOpenPrivateInfo()));
//        assertThat(updatedUser.isCertification(), is(user.isCertification()));
//
//
//    }
//
//
//    @Test
//    public void delete() {
//
//    }
//
//}
