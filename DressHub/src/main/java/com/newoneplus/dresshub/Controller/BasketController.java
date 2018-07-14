package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.Basket;
import com.newoneplus.dresshub.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/basket")
@AllArgsConstructor
public class BasketController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/search")
    public Page<Basket> getBasketList(@RequestParam(value = "page", defaultValue="1") int page){
        return productService.getBasketList(page-1);
    }

    @PostMapping
    public Basket create(@RequestBody Basket basket){
        return productService.createBasket(basket);
    }

    @PutMapping
    public void update(@RequestBody Basket basket){
        productService.createBasket(basket);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String checkUser(NullPointerException e){
        return "403";
    }

}
