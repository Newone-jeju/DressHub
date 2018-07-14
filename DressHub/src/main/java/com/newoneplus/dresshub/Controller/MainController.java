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


//    //TODO 나중에 프론트 위임
//    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
//    public String getProductDetail(@RequestParam(value = "productId") int productId, Model model) throws ClassNotFoundException {
//        model.addAttribute("productId", productId);
//        return "product_details";
//    }

    //TODO 나중에 Post로 바꿀것
    @RequestMapping(value = "/thumbUp", method = RequestMethod.GET)
    @ResponseBody
    public Product likeCreate(@RequestParam(value = "productId") Integer productId , @RequestParam(value = "state") boolean state)  {
        //TODO develop이랑 합치면 넣기 !

//        User user = new User();
//        user.setUid("aaaa");
        if(AuthorizationService.getCurrentUser()==null){
            new NullPointerException();
        }
            ThumbUp thumbUp = new ThumbUp();
            thumbUp.setLiker(AuthorizationService.getCurrentUser().getUid());
            Product product =productService.getProduct(productId);
            thumbUp.setProduct(product);
            if (state) {
                productService.insertThumup(thumbUp);
            } else {
                productService.deleteThumup(thumbUp);
            }


        return productService.getProduct(product.getId());

    }


    @RequestMapping(value = "/thumbUp/search", method = RequestMethod.GET)
    @ResponseBody
    public Page<Product> getThumbUpProductList(@RequestParam(value = "page", defaultValue = "1") int page) {
        return productService.getThumbUpProductList(page-1, AuthorizationService.getCurrentUser());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String checkUser(NullPointerException e){
        return "403";
    }
}
