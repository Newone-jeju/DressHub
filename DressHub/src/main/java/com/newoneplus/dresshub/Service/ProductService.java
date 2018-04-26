package com.newoneplus.dresshub.Service;


import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ProductDao;
import com.newoneplus.dresshub.Model.ProductImage;
import com.newoneplus.dresshub.Model.ProductImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    private ProductImageDao productImageDao;


    public void insertProductImage(ProductImage productImage) {
        productImageDao.insert(productImage);
    }

    public void insertProduct(Product product) {
        productDao.insert(product);
    }

    public ArrayList<ProductImage> getProductImageList(int id){
        return productImageDao.getProductImageList(id, "ID DESC");
    }
}
