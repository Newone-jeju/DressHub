package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class LoginController {
//
//    // login
//    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
//    public String login(Authentication authentication){
//        if (authentication != null) {
//            return "redirect:/";
//        }
//        return "login";
//    }
//
//
//
//    @GetMapping("/test")
//    public String test() {
//        return "test";
//    }
//
//    @GetMapping("/loginSuccess")
//    public String cookieTest(HttpServletRequest httpServletRequest,HttpServletResponse res, Authentication authentication) {
//        if (authentication != null) {
//            Cookie cookie = new Cookie("uid", AuthorizationService.getCurrentUser().getUid());
//            cookie.setPath("/");
//            res.addCookie(cookie);
//            for (Cookie c : httpServletRequest.getCookies()) {
////                log.info(c.getName());
//                log.info(c.getValue());
//            }
//            return "redirect:/";
//        }
//        return "redirect:/";
//    }


}
