package com.newoneplus.dresshub.Service;



import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ProductDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MainService {

//    productDao가 bean에 등록되어야 autowired를 쓸 수 있음
    @Autowired
    private ProductDao productDao;

    public ArrayList<Product> getProductList() throws ClassNotFoundException {
        return productDao.getList("ID DESC");

    }



}
