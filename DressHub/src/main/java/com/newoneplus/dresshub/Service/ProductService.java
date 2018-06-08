package com.newoneplus.dresshub.Service;


import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.*;

import com.newoneplus.dresshub.Repository.BasketRepository;
import com.newoneplus.dresshub.Repository.ProductImageRepository;
import com.newoneplus.dresshub.Repository.ProductRepository;
import com.newoneplus.dresshub.Repository.ThumbUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

 
    @Autowired
    ThumbUpRepository thumbUpRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ProductImageRepository productImageRepository;

    //product 등록
    public void createProduct(Product product) {
        ImageProcesser imageProcesser = new ImageProcesser();
        long product_id = productRepository.save(product).getId();
        product.setId( product_id);
        String filename = "";
        // 첨부파일(상품사진)이 있으면
        if (!product.getImage().get(0).isEmpty()) {
            for (int i = 0; i < product.getImage().size(); i++) {
                filename = product.getImage().get(i).getOriginalFilename();
                String path = System.getProperty("user.dir") + "/out/main/resources/static/product_image/";
                ProductImage productImage = new ProductImage();
                productImage.setProductId(product.getId());
//              맨처음 파일만   파일 크기 3가지로 구분 해서 db및 file 저장
                if (i == 0) {
                    try {
                        product.setThumbnailImage("origin" + filename);
                        BufferedImage image = imageProcesser.getMediumImage(product.getImage().get(i).getInputStream());
                        String mediumpath = "medium" + filename;
                        ImageIO.write(image, "jpg", new File(path + mediumpath));
                        productImage.setImage(mediumpath);
                        productImage.setImageSize("중간");
                        productImageRepository.save(productImage);
                        BufferedImage image2 = imageProcesser.getSmallImage(product.getImage().get(i).getInputStream());
                        String smallpath = "small" + filename;
                        ImageIO.write(image2, "jpg", new File(path + smallpath));
                        productImage.setImage(smallpath);
                        productImage.setImageSize("작은");
                        productImageRepository.save(productImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        new File(path).mkdirs(); // 디렉토리 생성
                        // 임시디렉토리(서버)에 저장된 파일을 지정된 디렉토리로 전송
                        String originpath = "origin" + filename;
                        product.getImage().get(i).transferTo(new File(path + originpath));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                productImage.setImage("origin" + filename);
                productImage.setImageSize("일반");
                productImageRepository.save(productImage);
            }
            productRepository.save(product);
        }
    }

    public List<Product> getProductList(){
        return productRepository.findAllByOrderByIdDesc();
    }
    // productid에 맞는 프로덕트 불러오기
    public Product getProduct(long id)  {
        return productRepository.findById(id);
    }


    //product이미지리스트 프로덕트id에 맞게 불러오기
    public List<ProductImage> getProductImageList(long id){ return productImageRepository.findAllByProductIdOrderByIdDesc(id); }

    //product이미지리스트 모두 정렬해서 불러오기
    public List<ProductImage> getProductImageList(){
        return productImageRepository.findAllByOrderByIdDesc();
    }

//   카테고리와 페이징처리를 위한 상품 불러오기
    public Page<Product> getProductList(int page, String category, String array) {
        HashMap<String, Object> map = new HashMap<>();
        if(category.equals("null")) {
            category = "";
        }
        User user = new User();
        user.setId("user1");
        PageRequest pageRequest = PageRequest.of(page, 25, Sort.Direction.ASC, "id");
        //여기에 좋아요와 조인을 해야한다.
            return productRepository.findAllByCatetoryJoinThumbUpByUser(category, user.getId(), pageRequest);
        //Todo 현재 user1로 임시로 지정 나중에 보안정책 완료 후 User가져오는 것으로 수정
    };


    //유저에 대한 장바구니 불러오기
    public Page<Basket> getBasketList(int page){
        User user = new User();
        user.setId("user1");
        PageRequest pageRequest = PageRequest.of(page, 25, Sort.Direction.DESC, "id");
        return basketRepository.findAllByUserJoinProduct(user.getId(), pageRequest);
    }


    //좋아요 등록하기
    public void insertThumup(ThumbUp thumbUp){
        thumbUpRepository.save(thumbUp);
        Product product = productRepository.findById(thumbUp.getProduct().getId());
        product.setLikes(product.getLikes()+1);
        productRepository.save(product);
    }

    //좋아요 삭제하기
    public void deleteThumup( ThumbUp thumbUp) {
        thumbUpRepository.deleteByUserAndProduct(thumbUp.getUserId(), thumbUp.getProduct());
        Product product = productRepository.findById(thumbUp.getProduct().getId());
        product.setLikes(product.getLikes()-1);
        productRepository.save(product);
    }

    public Page<Product> getThumbUpProductList(int page, User user) {
        PageRequest pageRequest = PageRequest.of(0, 25, Sort.Direction.DESC, "id");
        return productRepository.findAllByUser(user.getId(),pageRequest);
    }
}
