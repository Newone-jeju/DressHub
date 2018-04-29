package com.newoneplus.dresshub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;


    @Override
    public boolean loginCheck(String id, String password) {
        HttpSession httpSession = null;
        User user = null;
        boolean result = userDao.loginCheck(id, password);
        if (result){
            User user1 = viewUser(user);
            httpSession.setAttribute("userId",user1.getId());
            httpSession.setAttribute("userName", user1.getName());
        }
        return result;
    }

    @Override
    public User viewUser(User user) {
        return userDao.viewUser(user);
    }

    @Override
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }
}
