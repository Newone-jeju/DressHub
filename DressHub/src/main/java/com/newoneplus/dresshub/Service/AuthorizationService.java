//package com.newoneplus.dresshub.Service;
//
//import com.newoneplus.dresshub.Config.MyAuthentication;
//import com.newoneplus.dresshub.Model.User;
//import com.newoneplus.dresshub.Model.UserDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.sql.SQLException;
//
//@Service
//public class AuthorizationService {
//    @Autowired
//    UserDao userDao;
//
//    public User login(String id, String password) throws SQLException, ClassNotFoundException {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        User user = userDao.get(id);
//        if(user == null) return null;
//        if(bCryptPasswordEncoder.matches(password, user.getPassword()) == false) return null;
//        return user;
//    }
//
//    public static User getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof MyAuthentication)
//            return ((MyAuthentication) authentication).getUser();
//        return null;
//    }
//
//    public static void setCurrentUser(User user) {
//        ((MyAuthentication)
//                SecurityContextHolder.getContext().getAuthentication()).setUser(user);
//    }
//
//
//}
