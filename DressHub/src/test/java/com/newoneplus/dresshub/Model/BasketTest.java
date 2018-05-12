package com.newoneplus.dresshub.Model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class BasketTest {
    private BasketDao basketDao;
    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        basketDao= applicationContext.getBean("basketDao", BasketDao.class);
    }

    @Test
    public void get(){
       Basket basket =basketDao.get(1);

//        assertThat(basket.getUserId(), is(""));
    }

    @Test
    public void getBasketList(){
        HashMap<String,Object > basketList = basketDao.getBasketList("user1");
        assertThat(basketList.size(), is(1));
    }

    @Test
    public void add(){
        Basket basket = new Basket();
        basket.setUserId("user1");
        basket.setProductId(60);

        int id = basketDao.insert(basket);

        Basket inserBasket = basketDao.get(id);

        assertThat(inserBasket.getId(), is(3));
        assertThat(inserBasket.getUserId(), is("user1"));
        assertThat(inserBasket.getProductId(), is(60));
    }

    @Test
    public void update(){
        Basket basket = new Basket();
        basket.setUserId("user1");
        basket.setProductId(60);

        int id = basketDao.insert(basket);

        basket.setId(id);
        basket.setProductId(59);


        basketDao.update(basket);
        Basket updateBasket = basketDao.get(id);

        assertThat(updateBasket.getId(), is(id));
        assertThat(updateBasket.getProductId(), is(59));
    }

    @Test
    public void delete(){
        Basket basket = new Basket();
        basket.setUserId("user1");
        basket.setProductId(60);

        int id = basketDao.insert(basket);

        basketDao.delete(id);

        Basket deleteBasket  = basketDao.get(id);
        assertThat(deleteBasket, nullValue());
    }
}
