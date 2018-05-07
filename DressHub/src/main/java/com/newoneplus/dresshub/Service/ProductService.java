package com.newoneplus.dresshub.Service;


import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ProductDao;
import com.newoneplus.dresshub.Model.ProductImage;
import com.newoneplus.dresshub.Model.ProductImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImageDao productImageDao;


    public void insertProductImage(ProductImage productImage) {
       int a =productImageDao.insert(productImage);
    }

    public Integer insertProduct(Product product) {
       return productDao.insert(product);
    }

    public void updateProduct(Product product){
        productDao.update(product);
    }

    public ArrayList<ProductImage> getProductImageList(int id){
        return productImageDao.getProductImageList(id, "ID DESC");
    }
    public ArrayList<ProductImage> getProductImageList(){
        return productImageDao.getProductImageList("ID DESC" );
    }
    public HashMap<String,Object > getProductList(int page, String category, String array ){
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", productDao.getList(page,category,array));
        map.put("page", productDao.getCount(category));
        return map;
    };

}
