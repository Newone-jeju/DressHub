package com.newoneplus.dresshub.Controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebAppConfiguration
//@ContextConfiguration(classes = RestMemberControllerTest.class, loader = AnnotationConfigContextLoader.class)
public class RestMemberControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;


    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    public void loginTest() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUid("test");
        request.setPassword("test");
        ObjectMapper om = new ObjectMapper();
        mvc.perform(post("/rest/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(om.writeValueAsString(request)))
                .andExpect(status().isOk()) .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.username", is(request.getUid())))
                .andExpect(jsonPath("$.roles[*].authority", hasItem("USER")));
    }




}
