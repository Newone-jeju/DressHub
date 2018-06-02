package com.newoneplus.dresshub.Controller;

        import com.newoneplus.dresshub.Model.DaoFactory;
        import com.newoneplus.dresshub.Model.Product;
        import com.newoneplus.dresshub.Model.ProductDao;
        import com.newoneplus.dresshub.Service.MainService;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.SpringBootConfiguration;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.annotation.AnnotationConfigApplicationContext;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
        import org.springframework.test.context.web.WebAppConfiguration;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.MockMvcBuilder;
        import org.springframework.test.web.servlet.setup.MockMvcBuilders;
        import org.springframework.web.context.WebApplicationContext;

        import java.util.ArrayList;

        import static org.mockito.Mockito.verify;
        import static org.mockito.Mockito.verifyNoMoreInteractions;
        import static org.mockito.Mockito.when;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DaoFactory.class)
@WebAppConfiguration
public class MainControllerTest {


    @Mock
    MainService mainService;
    @InjectMocks
    MainController mainController;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws ClassNotFoundException {
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(mainController).build();
    }


//    @Test
//    public void getProductListTest() throws Exception {
//        mockMvc.perform(get("/products")).andExpect(status().isOk());
//
//        verify(mainService).getProductList();
//        verifyNoMoreInteractions(mainService);
//    }
}