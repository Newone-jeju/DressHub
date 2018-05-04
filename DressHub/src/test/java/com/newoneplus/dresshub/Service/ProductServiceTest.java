package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.Model.DaoFactory;
import com.newoneplus.dresshub.Model.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductServiceTest {

    private ProductService productService;

    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        productService = applicationContext.getBean("productService", ProductService.class);
    }

    @Test
    public void productResister(){
        Product product = new Product();
        product.setCategory("categoryTest");
        product.setConsigmentEnd("2017-05-04");
        product.setConsigmentStart("2017-04-04");
        product.setContents("contentsTest");
        product.setCostPerDay(1);
        product.setDeleveryType("deleveryTypeTest");
        product.setDeposit(2);
        product.setThumbnailImage(null);
        product.setName("nameTest");
        product.setProviderId("user1");
        product.setSalePrice(3);
        product.setState("stateTest");

        Integer insertedId = productService.insert(product);
    }

}
