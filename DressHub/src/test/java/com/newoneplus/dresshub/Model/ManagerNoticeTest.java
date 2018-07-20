package com.newoneplus.dresshub.Model;

import org.apache.catalina.Manager;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.internal.configuration.GlobalConfiguration.validate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagerNoticeTest {
    private static final String PATH = "/notice";
    @Autowired
    private TestRestTemplate restTemplate;
    @Before
    public void setup() {
    }

    @Test
    public void get(){
        String title = "f";
        String content = "f";
        String writer = "f";

        ManagerNotice mnNotice = restTemplate.getForObject(PATH + "/" + 1, ManagerNotice.class);
        assertThat(mnNotice.getTitle(), is(title));
        assertThat(mnNotice.getContent(), is(content));
        assertThat(mnNotice.getWriter(), is(writer));
    }

    @Test
    public void getList(){
        List<ManagerNotice> managerNotices = restTemplate.getForObject(PATH + "/list", List.class);
        assertThat(managerNotices, not(IsEmptyCollection.empty()));
    }

    @Test
    public void create() {
       ManagerNotice mnNoticeForCreate = new ManagerNotice();
       mnNoticeForCreate.setTitle("공지사항");
       mnNoticeForCreate.setContent("공지사항");
       mnNoticeForCreate.setWriter("운영자");

       ManagerNotice mnNotice = restTemplate.postForObject(PATH, mnNoticeForCreate, ManagerNotice.class);
       mnNoticeForCreate.setId(mnNotice.getId());
       assertThat(mnNotice, is(mnNoticeForCreate));
    }
}
