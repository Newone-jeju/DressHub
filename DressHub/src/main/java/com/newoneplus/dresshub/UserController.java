package com.newoneplus.dresshub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    User user;

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
//    @ResponseBody
    public String login(){
        return "login";
    }

    @RequestMapping("/loginCheck")
    public String loginCheck(HttpSession httpSession, HttpServletRequest httpServletRequest){
        boolean result = userService.loginCheck(httpServletRequest.getParameter("id"),
                httpServletRequest.getParameter("password"));
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




}
