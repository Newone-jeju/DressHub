package com.newoneplus.dresshub.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Slf4j
public class LoginController {

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public String login(Authentication authentication){
        if (authentication != null) {
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public String test(){
        return "test";
    }


}
