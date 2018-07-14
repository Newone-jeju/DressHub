package com.newoneplus.dresshub.Service;


import com.newoneplus.dresshub.Model.*;

import com.newoneplus.dresshub.Repository.BasketRepository;
import com.newoneplus.dresshub.Repository.ProductImageRepository;
import com.newoneplus.dresshub.Repository.ProductRepository;
import com.newoneplus.dresshub.Repository.ThumbUpRepository;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    ThumbUpRepository thumbUpRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BasketRepository basketRepository;




    public Page<Product> getProductList(int page, String category, String provider) {
        PageRequest pageRequest = PageRequest.of(page, 25, Sort.Direction.DESC, "id");
        if(provider.equals("")){
            return productRepository.findAllByCategoryContaining(category, pageRequest);
        }else{
            return productRepository.findAllByProvider(provider, pageRequest);
        }
    }



    public Page<Basket> getBasketList(int page) throws NullPointerException {
        if (AuthorizationService.getCurrentUser() == null) {
            new NullPointerException();
        }
        PageRequest pageRequest = PageRequest.of(page, 25, Sort.Direction.DESC, "id");
        return basketRepository.findAllByHolder(AuthorizationService.getCurrentUser().getUid(), pageRequest);
    }



    //좋아요 누르기
    public Product clickThumup(ThumbUp thumbUp) {
        Optional<ThumbUp> opthionalThumbUp = thumbUpRepository.findByLikerAndProduct(thumbUp.getLiker(), thumbUp.getProduct());
        Product product = productRepository.findById(thumbUp.getProduct()).get();
        if (!opthionalThumbUp.isPresent()) {
            //존재하지않는다면
            thumbUpRepository.save(thumbUp);
            productUpdate(product, product.getLikes() + 1);
        } else {
            //존재한다면
            thumbUpRepository.delete(opthionalThumbUp.get());
            productUpdate(product, product.getLikes() - 1);
        }
        return product;
    }
    //장바구니 누르기
    public Product clickBasket(Basket basket) {
        Optional<Basket> opthionalBasket = basketRepository.findByHolderAndProduct(basket.getHolder(), basket.getProduct());
        Product product = productRepository.findById(basket.getProduct()).get();
        if (!opthionalBasket.isPresent()) {
            //존재하지않는다면
            basketRepository.save(basket);
        } else {
            //존재한다면
            basketRepository.delete(opthionalBasket.get());
        }
        return product;
    }


    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String checkUser(NullPointerException e) {
        return "403";
    }


    private void productUpdate(Product product, int i) {
        product.setLikes(i);
        productRepository.save(product);
    }
}
