package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.Product;
//import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Slf4j
public class MainController {
    // main
    @Autowired
    private MainService mainService;

//    @RequestMapping("/")
//    public String test(HttpServletResponse res, Authentication authentication) {
//        if (authentication != null){
//            Cookie cookie = new Cookie("uid", AuthorizationService.getCurrentUser().getUid());
//            cookie.setPath("/");
//            res.addCookie(cookie);
//        }
//        return "index.html";
//    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProductList() throws ClassNotFoundException {
        return mainService.getProductList();
    }

    @RequestMapping(value = "/products/new", method = RequestMethod.GET)
    public String productform(){
        return "productform";
    }


    // User 인증정보 test
    @RequestMapping(value = "/getuser" , method = RequestMethod.GET)
    public void getUser() {
        log.info(String.valueOf(AuthorizationService.getCurrentUser().getResisterDate()));
        log.info(AuthorizationService.getCurrentUser().getIntroduce());
    }
}
