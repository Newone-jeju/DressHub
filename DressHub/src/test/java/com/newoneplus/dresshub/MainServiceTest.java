package com.newoneplus.dresshub;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class MainServiceTest {
    private MainService mainService;

    @Before
    public void setup() throws ClassNotFoundException {

        mainService= new MainService();
    }


    @Test
    public void getProductList() throws ClassNotFoundException {
        ArrayList<Product> productList = new ArrayList<>();
        productList= mainService.getProductList();
        assertThat(productList.size(), is(4));
    }
}
