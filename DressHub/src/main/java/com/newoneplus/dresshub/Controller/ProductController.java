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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String insert(@ModelAttribute Product product) throws IOException {
        ImageProcesser imageProcesser = new ImageProcesser();
       int product_id= productService.insertProduct(product);
       product.setId(product_id);
        String filename = "";
        // 첨부파일(상품사진)이 있으면
        if(!product.getImage().isEmpty()){
            for(int i =0; i<product.getImage().size(); i++){

                filename = product.getImage().get(i).getOriginalFilename();
                // 개발디렉토리 - 파일 업로드 경로
                //String path = "C:\\Users\\doubles\\Desktop\\workspace\\gitSpring\\"
                //                + "spring02\\src\\main\\webapp\\WEB-INF\\views\\images";
                // 배포디렉토리 - 파일 업로드 경로
                String path = "C:\\workspace\\project\\DressHub\\DressHub\\src\\main\\resources\\static\\product_image\\";
                try {
                    new File(path).mkdirs(); // 디렉토리 생성
                    // 임시디렉토리(서버)에 저장된 파일을 지정된 디렉토리로 전송
                    String originpath = "origin"+filename;
                    product.getImage().get(i).transferTo(new File(path+originpath));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ProductImage productImage = new ProductImage();
                productImage.setProductId(product.getId());


                if(i==0){
                    product.setThumbnailImage(filename);
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

                }
                productImage.setImage(filename);
                productImage.setImageSize("일반");
                productService.insertProductImage(productImage);


            }
            productService.updateProduct(product_id, product);

        }
        return "/products";
    }

    @RequestMapping(value = "/productImages/{paramid}", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductImage> getProductImages(@PathVariable int paramid){
        return productService.getProductImageList(paramid);
    }

    @RequestMapping(value = "/productImages", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductImage> getProductImageList(){
        return productService.getProductImageList();
    }
}
