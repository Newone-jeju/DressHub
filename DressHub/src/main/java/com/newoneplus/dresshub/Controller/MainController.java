package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.Product;
//import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Model.ThumbUp;
import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Service.AuthorizationService;

import com.newoneplus.dresshub.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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


//    //TODO 나중에 프론트 위임
//    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
//    public String getProductDetail(@RequestParam(value = "productId") int productId, Model model) throws ClassNotFoundException {
//        model.addAttribute("productId", productId);
//        return "product_details";
//    }
    @RequestMapping(value = "/thumbUp", method = RequestMethod.POST)
    public Product likeCreate(@RequestBody Product product, @RequestParam(value = "state") boolean state) throws ClassNotFoundException {
        //TODO develop이랑 합치면 넣기 !
        User user = new User();
        user.setUid("user1");
        ThumbUp thumbUp = new ThumbUp();
        thumbUp.setUid(user.getUid());
        thumbUp.setProduct(product);
        if (state) {
            productService.insertThumup(thumbUp);
        } else {
            productService.deleteThumup(thumbUp);
        }
        return productService.getProduct(product.getId());

    }


    @RequestMapping(value = "/thumbUp/search", method = RequestMethod.GET)
    public Page<Product> getThumbUpProductList(@RequestParam(value = "page", defaultValue = "1") int page) {
        User user = new User();
        user.setUid("user1");
        return productService.getThumbUpProductList(page, user);
    }

}
