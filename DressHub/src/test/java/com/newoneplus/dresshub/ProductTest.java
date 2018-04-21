package com.newoneplus.dresshub;

import org.apache.tomcat.jni.Time;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class ProductTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        int id = 1;
        ProductDao productDao = new ProductDao();
        Product product = productDao.get(id);
    }

    @Test
    public void getList(){
    }

    @Test
    public void insertUpdateDelete(){
        ProductDao productDao = new ProductDao();
        Product product = new Product();
        Date date = new Date(Time.now());
        product.setCategory("categoryTest");
        product.setConsigmentEnd(date);
        product.setConsigmentStart(date);
        product.setContents("contentsTest");
        product.setCostPerDay(1);
        product.setDeleveryType("deleveryTypeTest");
        product.setDeposit(2);
        product.setImageUrl("imageUrlTest");
        product.setName("nameTest");
        product.setProviderId("prividerTest");
        product.setSalePrice(3);
        product.setState("stateTest");
        
    }
}
