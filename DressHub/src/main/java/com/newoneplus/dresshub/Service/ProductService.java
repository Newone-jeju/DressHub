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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Product createProduct(Product product) {
        return  productRepository.save(product);
    }

    //product수정
    public void updateProduct(Product product){
        productRepository.save(product);
    }
    //product삭제
    public void deleteProduct(Product product) {
        productRepository.delete(product);
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

        PageRequest pageRequest = PageRequest.of(page, 25, Sort.Direction.DESC, "id");
        if(AuthorizationService.getCurrentUser() ==null){
            return productRepository.findAllByCatetoryJoinThumbUpByUid(category, "null", pageRequest);
        }else {
            return productRepository.findAllByCatetoryJoinThumbUpByUid(category, AuthorizationService.getCurrentUser().getUid(), pageRequest);
        }


        //여기에 좋아요와 조인을 해야한다.

        //Todo 현재 user1로 임시로 지정 나중에 보안정책 완료 후 User가져오는 것으로 수정
    };


    //유저에 대한 장바구니 불러오기
    public Page<Basket> getBasketList(int page) throws NullPointerException{
        if(AuthorizationService.getCurrentUser()==null){
            new NullPointerException();
        }
        PageRequest pageRequest = PageRequest.of(page, 25, Sort.Direction.DESC, "id");
        return basketRepository.findAllByUserJoinProduct(AuthorizationService.getCurrentUser().getUid(), pageRequest);
    }

    public Basket createBasket(Basket basket){
        return  basketRepository.save(basket);
    }


    //TODO 나중에 post로 바꿀 시 바꿔야함
    //좋아요 등록하기
    public void insertThumup(ThumbUp thumbUp){
        thumbUpRepository.save(thumbUp);
//        Product product = productRepository.findById(thumbUp.getProduct().getId());
//        product.setLikes(thumbUp.getProduct().getLikes()+1);
        Product product = thumbUp.getProduct();
        if(product.getLikes()==null){
            product.setLikes(0);
        }
        product.setLikes(product.getLikes()+1);
        productRepository.save(product);
    }

    //좋아요 삭제하기
    public void deleteThumup( ThumbUp thumbUp) {
        thumbUpRepository.deleteByUidAndProduct(thumbUp.getUid(), thumbUp.getProduct());
        Product product = productRepository.findById(thumbUp.getProduct().getId());
        product.setLikes(product.getLikes()-1);
        productRepository.save(product);
    }

    public Page<Product> getThumbUpProductList(int page, User user) {
        PageRequest pageRequest = PageRequest.of(page, 25, Sort.Direction.DESC, "id");
        return productRepository.findAllByUid(user.getUid(),pageRequest);
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String checkUser(NullPointerException e){
        return "403";
    }

    public List<Product> getProductListByProvider(String provider) {
        return productRepository.findAllByProvider(provider);
    }
}
