package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Service.MainService;
import com.newoneplus.dresshub.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @RequestMapping("/")
    public String index( ){
        return "index";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProductList() throws ClassNotFoundException {
        return mainService.getProductList();
    }

    @RequestMapping(value = "/products/{paramid}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable int paramid) throws ClassNotFoundException {
        return mainService.getProduct(paramid);
    }

    @RequestMapping(value = "/products/new", method = RequestMethod.GET)
    public String productform(){
        return "productform";
    }


    @RequestMapping("/viewTest")
    public String viewTest(Model model){
        model.addAttribute("message",   "Hello Spring Boot thymeleaf");
        return "view";
    }

}
