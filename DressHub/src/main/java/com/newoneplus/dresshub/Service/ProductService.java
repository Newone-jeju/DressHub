package com.newoneplus.dresshub.Service;


import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.*;
import com.newoneplus.dresshub.Model.ThumbupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private BasketDao basketDao;
    @Autowired
    private ThumbupDao thumbupDao;

    //product 등록
    public void createProduct(Product product) throws IOException {
        ImageProcesser imageProcesser = new ImageProcesser();
        int product_id = productDao.insert(product);
        product.setId(product_id);
        String filename = "";
        // 첨부파일(상품사진)이 있으면
        if (!product.getImage().get(0).isEmpty()) {
            for (int i = 0; i < product.getImage().size(); i++) {

                filename = product.getImage().get(i).getOriginalFilename();
                String path = System.getProperty("user.dir") + "/src/main/resources/static/product_image/";
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
                        productImageDao.insert(productImage);
                        BufferedImage image2 = imageProcesser.getSmallImage(product.getImage().get(i).getInputStream());
                        String smallpath = "small" + filename;
                        ImageIO.write(image2, "jpg", new File(path + smallpath));
                        productImage.setImage(smallpath);
                        productImage.setImageSize("작은");
                        productImageDao.insert(productImage);
                    } catch (FileNotFoundException e) {
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
                productImageDao.insert(productImage);
            }
            productDao.update(product);
        }
    }

    public ArrayList<Product> getProductList() throws ClassNotFoundException {
        return productDao.getList("ID DESC");
    }
    // productid에 맞는 프로덕트 불러오기
    public Product getProduct(int id) throws ClassNotFoundException {
        return productDao.get(id);
    }


    //product이미지리스트 프로덕트id에 맞게 불러오기
    public ArrayList<ProductImage> getProductImageList(int id){ return productImageDao.getProductImageList(id, "ID DESC"); }

    //product이미지리스트 모두 정렬해서 불러오기
    public ArrayList<ProductImage> getProductImageList(){
        return productImageDao.getProductImageList("ID DESC" );
    }

//   카테고리와 페이징처리를 위한 상품 불러오기
    public HashMap<String,Object > getProductList(int page, String category, String array) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", productDao.getList(page,category,array));
        map.put("count", productDao.getCount(category));
        //Todo 현재 user1로 임시로 지정 나중에 보안정책 완료 후 User가져오는 것으로 수정
        User user = new User();
        user.setId("user1");
        try{
//            user= AuthorizationService.getCurrentUser();
            map.put("like", thumbupDao.getLikeProductIdList(user.getId()));
        }catch (NullPointerException e){
            map.put("like", null);
            e.printStackTrace();
            return map;
        }
        return map;
    };


    //유저에 대한 장바구니 불러오기
    public HashMap<String, Object> getBasketList(String userId){
        return basketDao.getBasketList(userId);
    }


    //좋아요 등록하기
    public void insertThumup(String userId, int productId){
        thumbupDao.insert(userId, productId);
        Product product = productDao.get(productId);
        product.setLikes(product.getLikes()+1);
        productDao.update(product);
    }

    //좋아요 삭제하기
    public void deleteThumup( String userId, int productId) {
        thumbupDao.delete(userId, productId);
        Product product = productDao.get(productId);
        product.setLikes(product.getLikes()-1);
        productDao.update(product);
    }

    public HashMap<String,Object> getThumbUpProductList(int page, User user) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", thumbupDao.getCount(user.getId()));
        map.put("list", thumbupDao.getThumbUpProductList(page, user));
        return map;
    }
}
