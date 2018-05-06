package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.DaoFactory;
import com.newoneplus.dresshub.Model.ProductDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainControllerTest {
    private ProductDao productDao;

    @Before
    public void setup() throws ClassNotFoundException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        productDao = applicationContext.getBean("productDao", ProductDao.class);    }

//    현재 요구사항이 적어 아직 Controller와 Service하는 일이 똑같다!!
}