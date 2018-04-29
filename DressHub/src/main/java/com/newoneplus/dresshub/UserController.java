package com.newoneplus.dresshub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    User user;

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping("/loginCheck")
    public String loginCheck(User user, HttpSession httpSession){
        boolean result = userService.loginCheck(user, httpSession);
        if (result == true){
            return "/";
        } else {
            return "login";
        }

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        userService.logout(session);
        return "login";
    }

//    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
//    public String signUp(HttpServletRequest httpServletRequest) throws ParseException, ClassNotFoundException {
//
//
//
//
//    }






}
