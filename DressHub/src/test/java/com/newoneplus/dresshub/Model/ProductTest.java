package com.newoneplus.dresshub.Model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
public class ProductTest {
    static final String ID_ASC = "ID ASC";
    static final String ID_DESC = "ID DESC";


    private ProductDao productDao;
    @Before
    public void setup() throws ClassNotFoundException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        productDao = applicationContext.getBean("productDao", ProductDao.class);
    }


    @Test
    public void get() throws SQLException, ClassNotFoundException {
        int id = 1;
        Product product = productDao.get(id);
    }

    @Test
    public void getList(){
        productDao.getList("ID DESC");
        //테스트할 방법이없노
    }


    //디비에 아무거나 들어가면 안되니까 3개 한꺼번에 테스트
    //넣고 업데이트해보고 지우기

    @Test
    public void insertUpdateDelete() throws ClassNotFoundException {
        Product product = new Product();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        product.setCategory("categoryTest");
        product.setConsigmentEnd(date);
        product.setConsigmentStart(date);
        product.setContents("contentsTest");
        product.setCostPerDay(1);
        product.setDeleveryType("deleveryTypeTest");
        product.setDeposit(2);
        product.setThumbnailImage("imageUrlTest");
        product.setName("nameTest");
        product.setProviderId("user1");
        product.setSalePrice(3);
        product.setState("stateTest");

        Integer insertedId = productDao.insert(product);
        //딜리트 테스트가 통과하지 못한경우 직업 지워야할 id출력
        System.out.println(insertedId);
        product.setId(insertedId);
        Product insertedProduct = productDao.get(insertedId);

        assertThat(product, is(insertedProduct));
        //여기까지 삽입관련 테스트

        product.setName("changedName");
        product.setCostPerDay(5);
        product.setDeposit(6);
        product.setSalePrice(7);
        productDao.update(product);

        Product updatedProduct = productDao.get(insertedId);

        assertThat(product, is(updatedProduct));

//        여기까지 갱신관련 테스트

        productDao.delete(insertedId);
        Product deletedProduct = productDao.get(insertedId);

        assertThat(deletedProduct, is(nullValue()));
//        삭제 테스트
    }
}
