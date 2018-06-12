package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.*;
import com.newoneplus.dresshub.Repository.ProductRepository;
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
import java.io.BufferedOutputStream;
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

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productService.getProduct(id);
    }
    @GetMapping(value = "/list/search")
    public List<Product> getProductProvider(@RequestParam String provider){
        return productService.getProductListByProvider(provider);
    }

    @PostMapping
    public Product productCreate(@RequestBody Product product) {
        log.info("***********************요청이가 오고 있습니다. **********************************8");
        return productService.createProduct(product);


    }

    @PutMapping
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
    public Page getProductList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "category", defaultValue = "캐쥬얼") String category, @RequestParam(value = "order", defaultValue = "id desc") String order) {
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
    public void createProductImage(@RequestParam("file") MultipartFile productImage) throws IOException {
        log.info("***********************이미지가 오고 있습니다. **********************************8");
        ImageProcesser imageProcesser = new ImageProcesser();
        String filename = productImage.getOriginalFilename();
        String path = System.getProperty("user.dir") + "/out/production/resources/static/product_image/";
        new File(path).mkdirs(); // 디렉토리 생성
        if(productImage ==null){
            return;
        }
        BufferedImage image2 = imageProcesser.getMediumImage(productImage.getInputStream());
        writeImage(filename, path, image2, "medium");

        BufferedImage image3 = imageProcesser.getSmallImage(productImage.getInputStream());
        writeImage(filename, path, image3, "small");

        productImage.transferTo(new File(path + "origin" + filename));
    }

    private void writeImage(String filename, String path, BufferedImage image2, String size) throws IOException {
        String sizeFileName = size + filename;
        ImageIO.write(image2, "jpg", new File(path + sizeFileName));
    }




}
