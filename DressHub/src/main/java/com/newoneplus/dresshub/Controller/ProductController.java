package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ProductImage;
import com.newoneplus.dresshub.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String insert(@ModelAttribute Product product) throws IOException, ParseException {

        ImageProcesser imageProcesser = new ImageProcesser();
       int product_id= productService.insertProduct(product);
       product.setId(product_id);
        String filename = "";
        // 첨부파일(상품사진)이 있으면
        if(!product.getImage().get(0).isEmpty()){
            for(int i =0; i<product.getImage().size(); i++){

                filename = product.getImage().get(i).getOriginalFilename();
                String path = "C:\\workspace\\project\\DressHub\\DressHub\\src\\main\\resources\\static\\product_image\\";
                ProductImage productImage = new ProductImage();
                productImage.setProductId(product.getId());
//              맨처음 파일만   파일 크기 3가지로 구분 해서 db및 file 저장
                if(i==0){
                    try {
                        product.setThumbnailImage("origin"+filename);
                        BufferedImage image = imageProcesser.getMediumImage(product.getImage().get(i).getInputStream());
                        String mediumpath = "medium"+filename;
                        ImageIO.write(image,"jpg", new File(path+mediumpath));
                        productImage.setImage(mediumpath);
                        productImage.setImageSize("중간");
                        productService.insertProductImage(productImage);
                        BufferedImage image2 = imageProcesser.getSmallImage(product.getImage().get(i).getInputStream());
                        String smallpath = "small"+filename;
                        ImageIO.write(image2,"jpg", new File(path+smallpath));
                        productImage.setImage(smallpath);
                        productImage.setImageSize("작은");
                        productService.insertProductImage(productImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        new File(path).mkdirs(); // 디렉토리 생성
                        // 임시디렉토리(서버)에 저장된 파일을 지정된 디렉토리로 전송
                        String originpath = "origin"+filename;
                        product.getImage().get(i).transferTo(new File(path+originpath));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                productImage.setImage("origin"+filename);
                productImage.setImageSize("일반");
                productService.insertProductImage(productImage);


            }
            productService.updateProduct(product);

        }
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
    public List<Product> getProductList(@RequestParam(value = "page") int page, @RequestParam(value = "category") String category, @RequestParam(value = "order") String order){
        return productService.getProductList(page, category, order);
    }

    @RequestMapping(value = "/productImages", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductImage> getProductImageList(){
        return productService.getProductImageList();
    }
}
