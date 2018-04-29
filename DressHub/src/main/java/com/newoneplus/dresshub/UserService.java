package com.newoneplus.dresshub;


import javax.servlet.http.HttpSession;

public interface UserService {
    public boolean loginCheck(String id, String password);
    public User viewUser(User user);
    public void logout(HttpSession httpSession);
}
