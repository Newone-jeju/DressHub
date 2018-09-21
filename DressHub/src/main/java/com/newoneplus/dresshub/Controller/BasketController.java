package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.Basket;
import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ThumbUp;
import com.newoneplus.dresshub.Repository.BasketRepository;
import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sun.swing.BakedArrayList;

import java.util.List;

@RestController
@RequestMapping(value = "/basket")
@Slf4j
@AllArgsConstructor
public class BasketController {
    @Autowired
    ProductService productService;
    @Autowired
    BasketRepository basketRepository;



    @PostMapping
    public Basket basketProcess(@RequestBody Basket basket)  {
        if(AuthorizationService.getCurrentUser()==null){
            new NullPointerException();
        }
        //TODO 나중에 user에 대해서도 rest로 바뀌면 이렇게 넣을필요없다. 프론트에서 넣어서 가져오니까
        basket.setHolder(AuthorizationService.getCurrentUser().getUid());
        return productService.clickBasket(basket);
    }



    @GetMapping(value = "/list/product")
    public List<Basket> getThumbUpListbyProduct(@RequestBody List<Integer> productIdList) {
        return basketRepository.findAllByHolderAndProductList(AuthorizationService.getCurrentUser().getUid(), productIdList);
    }

    @GetMapping(value = "/list/search")
    public Page<Basket> getBasketList(@RequestParam(value = "page", defaultValue="1") int page){
        return productService.getBasketList(page-1);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String checkUser(NullPointerException e){
        return "403";
    }

}
