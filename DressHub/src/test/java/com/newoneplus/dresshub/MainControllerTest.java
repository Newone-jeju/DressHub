package com.newoneplus.dresshub;

import org.junit.Before;
import org.junit.Test;

public class MainControllerTest {
    private ProductDao productDao;

    @Before
    public void setup() throws ClassNotFoundException {
        productDao = new ProductDao();
    }

    @Test
    public void getProductList() {
        productDao.getList("ID DESC");
    }
}