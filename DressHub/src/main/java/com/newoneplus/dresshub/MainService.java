package com.newoneplus.dresshub;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MainService {

//    productDao가 bean에 등록되어야 autowired를 쓸 수 있음
    private ProductDao productDao;
    private UserDao userDao;

    public ArrayList<Product> getProductList() throws ClassNotFoundException {
        productDao = new ProductDao();
        return productDao.getList("ID DESC");
    }

    public Product getProduct(int id) throws ClassNotFoundException {
        productDao = new ProductDao();
        return productDao.get(id);
    }
}