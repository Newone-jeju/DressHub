package com.newoneplus.dresshub.Controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    // login
    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public String login(Authentication authentication){
        if (authentication != null) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/test")
    public String loginTest(){
        return "test";
    }
}
