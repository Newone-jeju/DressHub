package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ProductImage;
import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    ProductService  productService;

    //=================================================prdouct CRUD

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable(value="id") int id) throws ClassNotFoundException {
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String productCreate(@ModelAttribute Product product) throws IOException, ParseException {
        productService.createProduct(product);
        return "productList";
    }
    @RequestMapping(value = "/products/new", method = RequestMethod.GET)
    public String productNew(){
        return "productform";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProductList() throws ClassNotFoundException {
        return productService.getProductList();
    }

    //TODO 업데이트, delete 구현 예정

    //============================================여기까지 CRUD

    //=============================================product 검색관련

    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getProductList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "category", defaultValue = "null") String category, @RequestParam(value = "order" , defaultValue = "id desc") String order){
        return productService.getProductList(page, category, order);
    }
    //=============================================여기까지 product 검색

    //==========================================productImage 검색관련
    @RequestMapping(value = "/productImages/search", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductImage> getProductImageList(@RequestParam(value = "productId",required = false) Integer paramId){
        if(paramId == null){
            return productService.getProductImageList();
        }else{
            return productService.getProductImageList(paramId);
        }
    }
    //=============================================productImage 여기까지

    //=============================================baskets 검색 관련
    @RequestMapping(value = "/baskets/search", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getBasketList(@RequestParam(value = "userId") String userId){
        return productService.getBasketList(userId);
    }

    //=============================================baskets 검색 여기까지

    //TODO 나중에 보안정책 정해지면 POST로 바꿔야 한다.
    @RequestMapping(value= "/thumbUp", method=RequestMethod.GET)
    @ResponseBody
    public Product likeCreate(@RequestParam(value="productId") int productId, @RequestParam(value="state") boolean state) throws ClassNotFoundException {

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

    //
    @RequestMapping(value="/thumbUp/search", method=RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getThumbUpProductList(@RequestParam(value = "page", defaultValue = "1") int page){
         User user = new User();
         user.setId("user1");
//        return productService.getProductList(page, category, order);
        return productService.getThumbUpProductList(page, user);
    }







    //TODO restapi에 맞지않지만 현재 이것보다 좋은 방법x 나중에 생각해 보자.
    @RequestMapping(value = "/productList", method= RequestMethod.GET)
    public String productListView(@RequestParam(value = "category", defaultValue = "10") String category, Model model){
        model.addAttribute("category", category);
        return "productList";
    }
    //TODO restapi에 맞지않지만 현재 이것보다 좋은 방법x 나중에 생각해 보자.
    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
    public String getProductDetail(@RequestParam(value="productId") int productId, Model model) throws ClassNotFoundException {
        model.addAttribute("productId", productId);
        return "product_details";
    }


//TODO 나중에 user정보 NULL 나올 때 로그인창으로 이동
    @ExceptionHandler(NullPointerException.class)
    public String requestLogin(NullPointerException e){
        return "login";
    }

}
