package com.newoneplus.dresshub;


import org.springframework.web.bind.annotation.*;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    private MainService mainService;


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProductList() throws ClassNotFoundException {
        mainService = new MainService();
        return mainService.getProductList();
    }

    @RequestMapping(value = "/products/{paramid}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int paramid) throws ClassNotFoundException {
        mainService = new MainService();
        return mainService.getProduct(paramid);
    }
}
