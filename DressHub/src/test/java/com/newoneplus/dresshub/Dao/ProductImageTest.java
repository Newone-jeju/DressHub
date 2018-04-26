package com.newoneplus.dresshub.Dao;

import com.newoneplus.dresshub.Model.DaoFactory;
import com.newoneplus.dresshub.Model.ProductImage;
import com.newoneplus.dresshub.Model.ProductImageDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProductImageTest {
    private ProductImageDao productImageDao;

    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        productImageDao= applicationContext.getBean("productImageDao", ProductImageDao.class);
    }


    @Test
    public void get(){
       ProductImage productImage =productImageDao.get(1);

       assertThat(productImage.getId(), is(1));
       assertThat(productImage.getImage(), is("C:\\workspace"));

    }

    @Test
    public void add(){
        ProductImage productImage = new ProductImage();
        productImage.setProductId(1);
        productImage.setImage("testurl");
        productImage.setImageSize("testsize");

        int id = productImageDao.insert(productImage);

        ProductImage insertProductImageDao = productImageDao.get(id);

        assertThat(insertProductImageDao.getId(), is(id));
        assertThat(insertProductImageDao.getImage(), is(productImage.getImage()));
        assertThat(insertProductImageDao.getImageSize(), is(productImage.getImageSize()));
    }

    @Test
    public void update(){
        ProductImage productImage = new ProductImage();
        productImage.setProductId(1);
        productImage.setImage("testurl");
        productImage.setImageSize("testsize");

        int id = productImageDao.insert(productImage);

        productImage.setImage("updateTestUrl");
        productImage.setImage("updateTestSize");
        productImageDao.update(productImage);

        ProductImage updateProductImage = productImageDao.get(id);
        assertThat(updateProductImage.getImage(), is(productImage.getImage()) );
        assertThat(updateProductImage.getImageSize(), is(productImage.getImageSize()) );
    }



}
