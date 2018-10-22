//package com.newoneplus.dresshub.Model;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.util.ArrayList;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.CoreMatchers.nullValue;
//import static org.junit.Assert.assertThat;
//
//public class ProductImageTest {
//    private ProductImageDao productImageDao;
//
//    @Before
//    public void setup(){
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//        productImageDao= applicationContext.getBean("productImageDao", ProductImageDao.class);
//    }
//
//
//    @Test
//    public void get(){
//       ProductImageController productImage =productImageDao.get(1);
//
//       assertThat(productImage.getId(), is(1));
//       assertThat(productImage.getImage(), is("C:\\workspace"));
//
//    }
//
//    @Test
//    public void add(){
//        ProductImageController productImage = new ProductImageController();
//        productImage.setProductId(1);
//        productImage.setImage("testurl");
//        productImage.setImageSize("testsize");
//
//        int id = productImageDao.insert(productImage);
//
//        ProductImageController insertProductImageDao = productImageDao.get(id);
//
//        assertThat(insertProductImageDao.getId(), is(id));
//        assertThat(insertProductImageDao.getImage(), is(productImage.getImage()));
//        assertThat(insertProductImageDao.getImageSize(), is(productImage.getImageSize()));
//    }
//
//    @Test
//    public void update(){
//        ProductImageController productImage = new ProductImageController();
//        productImage.setProductId(1);
//        productImage.setImage("testurl");
//        productImage.setImageSize("testSize");
//
//        int id = productImageDao.insert(productImage);
//
//        productImage.setImage("updateTestUrl");
//        productImage.setImageSize("updateTestSize");
//        productImage.setId(id);
//        productImageDao.update(productImage);
//
//        ProductImageController updateProductImage = productImageDao.get(id);
//        assertThat(updateProductImage.getImage(), is(productImage.getImage()) );
//        assertThat(updateProductImage.getImageSize(), is(productImage.getImageSize()) );
//    }
//
//
//    @Test
//    public void delete(){
//        ProductImageController productImage = new ProductImageController();
//        productImage.setProductId(1);
//        productImage.setImage("testurl");
//        productImage.setImageSize("testSize");
//
//        int id = productImageDao.insert(productImage);
//
//        productImageDao.delete(id);
//
//        ProductImageController deleteProductImage= productImageDao.get(id);
//
//        assertThat(deleteProductImage, nullValue());
//
//
//    }
//
//    @Test
//    public void getProductImageList(){
//        ArrayList<ProductImageController> productImageList= productImageDao.getProductImageList(1, "ID DESC");
//        assertThat(productImageList.size(), is(18));
//    }
//
//    @Test
//    public void getList(){
//        ArrayList<ProductImageController> productImagesList = productImageDao.getProductImageList("ID DESC");
//        assertThat(productImagesList.size(), is(18));
//    }
//}
