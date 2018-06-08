package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.*;
import com.newoneplus.dresshub.Repository.ThumbUpRepository;
import com.newoneplus.dresshub.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    ProductService  productService;


    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable(value="id") int id)  {
        return productService.getProduct(id);
    }


    @PostMapping
    public String productCreate(@RequestBody Product product){
        productService.createProduct(product);
        return "productList";
    }


    @GetMapping(value = "/list")
    public List<Product> getProductList() {
        return productService.getProductList();
    }

    @GetMapping(value="/search")
    public Page getProductList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "category", defaultValue = "null") String category, @RequestParam(value = "order" , defaultValue = "id desc") String order){
        return productService.getProductList(page-1, category, order);
    }


    @GetMapping(value = "/image/list/search")
    public List<ProductImage> getProductImageList(@RequestParam(value = "productId",required = false) Long paramId){
        if(paramId == null){
            return productService.getProductImageList();
        }else{
            return productService.getProductImageList(paramId);
        }
    }


    @RequestMapping(value= "/thumbUp", method=RequestMethod.POST)
    @ResponseBody
    public Product likeCreate(@RequestBody Product product , @RequestParam(value="state") boolean state) throws ClassNotFoundException {
        //TODO develop이랑 합치면 넣기 !
        User user = new User();
        user.setUid("user1");
        ThumbUp thumbUp = new ThumbUp();
        thumbUp.setUserId(user.getUid());
        thumbUp.setProduct(product);
        if(state){
            productService.insertThumup(thumbUp);
        }else{
            productService.deleteThumup(thumbUp);
        }
        return productService.getProduct(product.getId());

    }


    @RequestMapping(value="/thumbUp/search", method=RequestMethod.GET)
    @ResponseBody
    public Page<Product> getThumbUpProductList(@RequestParam(value = "page", defaultValue = "1") int page){
         User user = new User();
         user.setUid("user1");
        return productService.getThumbUpProductList(page, user);
    }


    //TODO 나중에 프론트 위임
    @RequestMapping(value = "/productList", method= RequestMethod.GET)
    public String productListView(@RequestParam(value = "category", defaultValue = "10") String category, Model model){
        model.addAttribute("category", category);
        return "productList";
    }


    //TODO 나중에 프론트 위임
    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
    public String getProductDetail(@RequestParam(value="productId") int productId, Model model) throws ClassNotFoundException {
        model.addAttribute("productId", productId);
        return "product_details";
    }



}
