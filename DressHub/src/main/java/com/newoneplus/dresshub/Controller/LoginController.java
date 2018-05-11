package com.newoneplus.dresshub.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public String test(){
        return "test";
    }


    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(){
        return "/join";
    }

}
