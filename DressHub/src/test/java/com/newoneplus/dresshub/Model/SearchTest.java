package com.newoneplus.dresshub.Model;

import com.newoneplus.dresshub.Repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchTest {

    private static final String PATH = "/searching";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void get(){
        ManagerNotice mn = new ManagerNotice();
        mn.setId(1);
        mn.setContent("hi");

        ManagerNotice mnNotice = restTemplate.postForObject(PATH, mn, ManagerNotice.class);

        Product product = new Product();
        product.setId(mn.getId());
        product.setContents(mn.getContent());

        Product p = restTemplate.getForObject(PATH + "/" + product.getId(), Product.class);

        assertThat(mnNotice.getContent(),is(p.getContents()));
    }
}
