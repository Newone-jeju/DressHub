package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.Basket;
import com.newoneplus.dresshub.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/baskets")
@AllArgsConstructor
public class BasketController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/search")
    public Page<Basket> getBasketList(@RequestParam(value = "page", defaultValue="1") int page){
        return productService.getBasketList(page-1);
    }



}
