package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public String save(User user) throws ClassNotFoundException {
        return userDao.insert(user);
    }
}
