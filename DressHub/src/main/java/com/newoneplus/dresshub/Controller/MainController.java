package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Service.MainService;
import com.newoneplus.dresshub.Data.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    private MainService mainService;


    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProductList() throws ClassNotFoundException {
        return mainService.getProductList();
    }

    @RequestMapping(value = "/api/products/{paramid}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int paramid) throws ClassNotFoundException {
        return mainService.getProduct(paramid);
    }


}
