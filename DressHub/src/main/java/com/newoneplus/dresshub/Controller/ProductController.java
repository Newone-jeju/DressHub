package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.*;
import com.newoneplus.dresshub.Repository.ThumbUpRepository;
import com.newoneplus.dresshub.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productService.getProduct(id);
    }


    @PostMapping(produces = "application/json; charset=UTF-8")
    public Product productCreate(@RequestBody Product product) {
        log.info(product.getCategory());
        return productService.createProduct(product);


    }

    @PutMapping(produces = "application/json; charset=UTF-8")
    public void productUpdate(@RequestBody Product product){
        productService.updateProduct(product);
    }

    @DeleteMapping(value = "/{id}")
    public void productDelete(@PathVariable Integer id){
        productService.deleteProduct(productService.getProduct(id));
    }

    @GetMapping(value = "/list")
    public List<Product> getProductList() {
        return productService.getProductList();
    }

    @GetMapping(value = "/search")
    public Page getProductList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "category", defaultValue = "null") String category, @RequestParam(value = "order", defaultValue = "id desc") String order) {
        return productService.getProductList(page - 1, category, order);
    }


    @GetMapping(value = "/image/list/search")
    public List<ProductImage> getProductImageList(@RequestParam(value = "productId", required = false) Long paramId) {
        if (paramId == null) {
            return productService.getProductImageList();
        } else {
            return productService.getProductImageList(paramId);
        }
    }

    @PostMapping(value = "/image")
    public void createProductImage(@RequestParam MultipartFile productImage) throws IOException {

        ImageProcesser imageProcesser = new ImageProcesser();
        String filename = productImage.getOriginalFilename();
        String path = System.getProperty("user.dir") + "/out/main/resources/static/product_image/";
        new File(path).mkdirs(); // 디렉토리 생성

        BufferedImage image = imageProcesser.getMediumImage(productImage.getInputStream());
        writeImage(filename, path, image, "medium");

        BufferedImage image2 = imageProcesser.getSmallImage(productImage.getInputStream());
        writeImage(filename, path, image2, "small");

        productImage.transferTo(new File(path + "origin" + filename));
    }

    private void writeImage(String filename, String path, BufferedImage image2, String size) throws IOException {
        String sizeFileName = size + filename;
        ImageIO.write(image2, "jpg", new File(path + sizeFileName));
    }

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


    //TODO 나중에 프론트 위임
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String productListView(@RequestParam(value = "category", defaultValue = "10") String category, Model model) {
        model.addAttribute("category", category);
        return "productList";
    }


    //TODO 나중에 프론트 위임
    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
    public String getProductDetail(@RequestParam(value = "productId") int productId, Model model) throws ClassNotFoundException {
        model.addAttribute("productId", productId);
        return "product_details";
    }


}
