package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.Product;
//import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Model.ThumbUp;
import com.newoneplus.dresshub.Service.AuthorizationService;

import com.newoneplus.dresshub.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class MainController {

    @Autowired
    ProductService productService;
    //TODO 나중에 프론트 위임
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String productListView(@RequestParam(value = "category", defaultValue = "10") String category, Model model) {
        model.addAttribute("category", category);
        return "productList";
    }




    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String checkUser(NullPointerException e){
        return "403";
    }
}
