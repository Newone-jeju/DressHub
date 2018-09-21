//package com.newoneplus.dresshub.Controller;
//
//import com.newoneplus.dresshub.Service.ReviewService;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//public class ReviewTest {
//    @Mock
//    ReviewService reviewService;
//    @InjectMocks
//    private CategoryController categoryController;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws ExceptiMon {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
//    }
//
//    @Test
//    public void testCategoryController() throws Exceptions {
//        when(categoryService.method1()).thenReturn(10);
//        mockMvc.perform(get("/category/list")).andExpect(status().isOk());
//
//        verify(categoryService).method1();
//        verifyNoMoreInteractions(categoryService);
//    }
//}
