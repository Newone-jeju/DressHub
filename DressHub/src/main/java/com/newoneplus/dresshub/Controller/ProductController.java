package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.Basket;
import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ProductImage;
import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    ProductService      productService;


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String insert(@ModelAttribute Product product) throws IOException, ParseException {
        productService.insertProduct(product);
//        임시로 폼으로 다시
        return "productList";
    }

    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getProductList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "category", defaultValue = "null") String category, @RequestParam(value = "order" , defaultValue = "id desc") String order){
        return productService.getProductList(page, category, order);
    }

    @RequestMapping(value = "/productImages", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductImage> getProductImageList(@RequestParam(value = "productId",required = false) Integer paramId){
        if(paramId == null){
            return productService.getProductImageList();
        }else{
            return productService.getProductImageList(paramId);
        }
    }

    @RequestMapping(value = "/productList", method= RequestMethod.GET)
    public String productListView(@RequestParam(value = "category", defaultValue = "10") String category, Model model){
        model.addAttribute("category", category);
        return "productList";
    }

    @RequestMapping(value = "/basketList", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getBasketList(@RequestParam(value = "userId") String userId){
        return productService.getBasketList(userId);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@RequestParam(value="productId") int productId) throws ClassNotFoundException {
        return productService.getProduct(productId);
    }

    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
    public String getProductDetail(@RequestParam(value="productId") int productId, Model model) throws ClassNotFoundException {
        model.addAttribute("productId", productId);
        return "product_details";
    }

    @RequestMapping(value= "/productAddLike", method=RequestMethod.GET)
    @ResponseBody
    public Product addLike(@RequestParam(value="productId") int productId, @RequestParam(value="state") boolean state) throws ClassNotFoundException {

        log.info("productId"+ productId);
        User user = new User();
        user.setId("user1");

        //TODO 나중에 보안정책 끝나면 가져와서 넣을 것
//        user= AuthorizationService.getCurrentUser();


        if(state ){
            productService.insertThumup(user.getId(), productId);
        }else{
            productService.deleteThumup(user.getId(), productId);
        }

        return productService.getProduct(productId);

    }
//TODO 나중에 user정보 NULL 나올 때 로그인창으로 이동
    @ExceptionHandler(NullPointerException.class)
    public String requestLogin(NullPointerException e){
        return "login";
    }

}
