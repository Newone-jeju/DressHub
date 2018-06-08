package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.Product;
//import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class MainController {
    // main
    @Autowired
    private MainService mainService;


    // User 인증정보 test
    @RequestMapping(value = "/getuser" , method = RequestMethod.GET)
    public void getUser() {
        log.info(AuthorizationService.getCurrentUser().getUid());

    }
}
