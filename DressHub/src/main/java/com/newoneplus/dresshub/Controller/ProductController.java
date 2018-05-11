package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ProductImage;
import com.newoneplus.dresshub.Service.ProductService;
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

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String insert(@ModelAttribute Product product) throws IOException, ParseException {
        productService.insertProduct(product);
//        임시로 폼으로 다시
        return "productform";

    }

    @RequestMapping(value = "/productImages/{paramid}", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductImage> getProductImages(@PathVariable int paramid){
        return productService.getProductImageList(paramid);
    }

    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getProductList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "category", defaultValue = "null") String category, @RequestParam(value = "order" , defaultValue = "id desc") String order){
        return productService.getProductList(page, category, order);
    }

    @RequestMapping(value = "/productImages", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductImage> getProductImageList(){
        return productService.getProductImageList();
    }

    @RequestMapping(value = "/productList", method= RequestMethod.GET)
    public String productListView(@RequestParam(value = "category", defaultValue = "10") String category, Model model){
        model.addAttribute("category", category);
        return "productList";
    }
}
