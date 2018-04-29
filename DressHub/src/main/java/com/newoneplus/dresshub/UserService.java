package com.newoneplus.dresshub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Service
public class UserService{

    @Autowired
    UserDao userDao;


    public boolean loginCheck(User user, HttpSession httpSession) {
        String id = user.getId();
        String password =user.getPassword();
        boolean result = userDao.loginCheck(id, password);
        if (result){
            User user1 = viewUser(user);
            httpSession.setAttribute("userId",user1.getId());
            httpSession.setAttribute("userName", user1.getName());
        }
        return result;
    }

    public User viewUser(User user) {
        return userDao.viewUser(user);
    }

    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }

    public void signUp() throws ClassNotFoundException, ParseException {
    }
}
