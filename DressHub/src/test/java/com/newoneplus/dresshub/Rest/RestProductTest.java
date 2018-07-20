package com.newoneplus.dresshub.Rest;

import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Service.ProductServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestProductTest {
    private static final String PATH = "/product";
    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders httpHeaders;
    @Before
    public void setup() {
        httpHeaders = new HttpHeaders();
    }


//    @Test
//    public void get(){
//        String uid = "testuser";
//        String password = "1234";
//
//
//        User user = restTemplate.getForObject(PATH + "/" + 1, User.class);
//
//        assertThat(user.getUid(), is(uid));
//        assertThat(user.getPassword(), is(password));
//
//
//    }

    @Test
    public void create(){
        //TODO DB에서 테스트유저 꼭 지우고 테스트할것!
        Product productForCreate = new Product();
        productForCreate.setName("123");
        productForCreate.setThumbnailImage("123");
        productForCreate.setContents("123");
        productForCreate.setCostPerDay(123);
        productForCreate.setDeposit(100);
        productForCreate.setSalePrice(100);
        productForCreate.setLocation("제주");
        productForCreate.setCategory("백원");
        productForCreate.setConsigmentStart("2018-07-18");
        productForCreate.setConsigmentEnd("2018-07-18");
        productForCreate.setProvider("강다희");
        productForCreate.setLikes(5);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//        productForCreate.setRegistrationDate(sdf.format(new Date()));

        productForCreate.setLeastLeaseDay(3);
        productForCreate.setSize("m");

        Product product = restTemplate.postForObject(PATH, productForCreate, Product.class);
        productForCreate.setId(product.getId());
        assertThat(productForCreate, is(product));

    }

//    @Test
//    public void duplicatedUidCreate(){
//        User userForCreate = new User();
//        userForCreate.setUid("duplicateuser");
//        userForCreate.setPassword("1234");
//        User user = restTemplate.postForObject(PATH + "/", userForCreate, User.class);
//
//        assertThat(user ,is(nullValue()));
//
//    }


//    @Test
//    //TODO testuser 지우고 테스트할것!
//    public void update(){
//        User userForUpdate = getCreatedUser();
//        userForUpdate.setPassword("4321");
////        context.notoken(PATH , HttpMethod.PUT, userForUpdate);
////        context.notOwner(PATH, HttpMethod.PUT, userForUpdate);
//
//        HttpEntity entity = new HttpEntity(userForUpdate, httpHeaders);
//        ResponseEntity<User> user= restTemplate.exchange(PATH, HttpMethod.PUT, entity , User.class);
//
//        validate(user.getBody(), userForUpdate);
//
//    }

//    @Test
//    public void invailidUidWhenUpdate(){
//        User userForUpdate = getCreatedUser();
//        userForUpdate.setUid("gg");
//
//        context.notoken(PATH , HttpMethod.PUT, userForUpdate);
//        context.notOwner(PATH, HttpMethod.PUT, userForUpdate);
//
//        HttpEntity entity = new HttpEntity(userForUpdate, httpHeaders);
//        ResponseEntity<User> user= restTemplate.exchange(PATH, HttpMethod.PUT, entity , User.class);
//
//        assertThat(user.getBody(), is(nullValue()));
//    }

    //TODO 계정 삭제는 논의 후 진행
//    @Test
//    public void delete(){
//        User user = restTemplate.getForObject(PATH + "/"+ getCreatedUser().getId(), User.class);
//        restTemplate.delete(PATH + "/" + user.getId());
//
//        User deletedUser = restTemplate.getForObject(PATH + "/" + user.getId(), User.class);
//
//        assertThat(deletedUser, is(nullValue()));
//
//    }



//    private User getCreatedUser() {
//        User user = new User();
//        user.setUid("testuser");
//        user.setPassword("1234");
//        return  restTemplate.postForObject(PATH + "/", user, User.class);
//    }

//    private void validate(User user1, User user2) {
//        assertThat(user2.getUid(), is(user1.getUid()));
//        assertThat(user2.getPassword(), is(user1.getPassword()));
//    }
}

