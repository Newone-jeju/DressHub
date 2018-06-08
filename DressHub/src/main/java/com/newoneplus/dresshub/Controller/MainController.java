package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class MainController {
    @Autowired
    private MainService mainService;
//    @RequestMapping("/")
//    public String index( ){
//        return "index";
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
        log.info(AuthorizationService.getCurrentUser().getUid());

    }
}
