package com.newoneplus.dresshub.Model;

import com.newoneplus.dresshub.Repository.ManagerNoticeRepository;
import org.apache.catalina.Manager;
import org.hamcrest.collection.IsEmptyCollection;
import org.hibernate.collection.internal.PersistentArrayHolder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// is(T value)
import static org.hamcrest.CoreMatchers.*;
// assertThat(String reason, boolean assertion);
import static org.hamcrest.MatcherAssert.*;
//import static org.mockito.internal.configuration.GlobalConfiguration.validate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagerNoticeTest {
    private static final String PATH = "/notice";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ManagerNoticeRepository mnRepository;
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

    @Test
    public void update() {
        ManagerNotice mnNoticeForUpdate = new ManagerNotice();
        mnNoticeForUpdate.setTitle("공지사항");
        mnNoticeForUpdate.setContent("공지사항");
        mnNoticeForUpdate.setWriter("운영자");

        ManagerNotice mnNotice = restTemplate.postForObject(PATH, mnNoticeForUpdate, ManagerNotice.class);
        Integer id = mnNotice.getId();
        if (mnRepository.existsById(id)) {
            String rewrite = "수정";

            mnNotice.setTitle(rewrite);
            mnNotice.setContent(rewrite);
            mnNotice.setWriter(rewrite);

            restTemplate.put(PATH, mnNotice);

            assertThat(mnNotice.getTitle(), is(rewrite));
            assertThat(mnNotice.getContent(), is(rewrite));
            assertThat(mnNotice.getWriter(), is(rewrite));
        }
    }

    @Test
    public void delete() {
        ManagerNotice mnNoticeForDelete = new ManagerNotice();
        Integer id = mnNoticeForDelete.getId();

        ManagerNotice mnNotice = restTemplate.getForObject(PATH + "/" + id, ManagerNotice.class);
        restTemplate.delete(PATH + "/" + id);

        ManagerNotice deleteNotice = restTemplate.getForObject(PATH + "/" + id, ManagerNotice.class);

        String dTitle = deleteNotice.getTitle();
        String dContent = deleteNotice.getContent();
        String dWriter = deleteNotice.getWriter();
        String dDate = deleteNotice.getDate();

        assertThat(dTitle, is(nullValue()));
        assertThat(dContent, is(nullValue()));
        assertThat(dWriter, is(nullValue()));
        assertThat(dDate, is(nullValue()));
    }
}
