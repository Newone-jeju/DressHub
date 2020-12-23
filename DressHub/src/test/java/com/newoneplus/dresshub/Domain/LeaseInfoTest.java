//package com.newoneplus.dresshub.Model;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.rmi.dgc.Lease;
//import java.util.ArrayList;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.CoreMatchers.nullValue;
//import static org.junit.Assert.assertThat;
//
//public class LeaseInfoTest {
//    LeaseInfoDao leaseInfoDao;
//    @Before
//    public void setup(){
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//        leaseInfoDao = (LeaseInfoDao) applicationContext.getBean("leaseInfoDao");
//    }
//
//
//
//    @Test
//    public void getInfosByUser(){
//        String userId = "testuser";
//
//        LeaseInfo leaseInfo = new LeaseInfo();
//        leaseInfo.setLeaseDay("1234-12-12");
//        leaseInfo.setLeaser(userId);
//        leaseInfo.setLeaseSite("제주시");
//        leaseInfo.setProduct(0);
//        leaseInfo.setReturnDay("1234-12-12");
//        leaseInfo.setReturnSite("제주시");
//        leaseInfo.setTotalPrice(15000);
//
//        ArrayList<LeaseInfo> receivedLeaseInfos = leaseInfoDao.getInfosByLeaser(userId);
//        assertThat(receivedLeaseInfos.get(0), is(leaseInfo));
//
//
//
//
//    }
//    @Test
//    public void getInfosByProduct(){
//        int productId = 0;
//
//        LeaseInfo leaseInfo = new LeaseInfo();
//        leaseInfo.setLeaseDay("1234-12-12");
//        leaseInfo.setLeaser("testuser");
//        leaseInfo.setLeaseSite("제주시");
//        leaseInfo.setProduct(0);
//        leaseInfo.setReturnDay("1234-12-12");
//        leaseInfo.setReturnSite("제주시");
//        leaseInfo.setTotalPrice(15000);
//
//        ArrayList<LeaseInfo> receivedLeaseInfos = leaseInfoDao.getInfosByProduct(productId);
//        assertThat(receivedLeaseInfos.get(0), is(leaseInfo));
//
//    }
//    @Test
//    public void getInfosByUserAndProduct(){
//        String userId = "testuser";
//        int productId = 0;
//
//        LeaseInfo leaseInfo = new LeaseInfo();
//        leaseInfo.setLeaseDay("1234-12-12");
//        leaseInfo.setLeaser("testuser");
//        leaseInfo.setLeaseSite("제주시");
//        leaseInfo.setProduct(0);
//        leaseInfo.setReturnDay("1234-12-12");
//        leaseInfo.setReturnSite("제주시");
//        leaseInfo.setTotalPrice(15000);
//
//        ArrayList<LeaseInfo> receivedLeaseInfos = leaseInfoDao.getInfosByUserAndProduct(userId, productId);
//        assertThat(receivedLeaseInfos.get(0), is(leaseInfo));
//    }
//
//    @Test
//    public void insertUpdateDelete(){
//        LeaseInfo leaseInfo = new LeaseInfo();
//        leaseInfo.setLeaser("testuser");
//        leaseInfo.setProduct(0);
//        leaseInfo.setReturnSite("null");
//        leaseInfo.setLeaseSite("제주시");
//        leaseInfo.setTotalPrice(15000);
//        leaseInfo.setReturnDay("null");
//        leaseInfo.setLeaseDay("1234-12-12");
//
//        int insertedId = leaseInfoDao.insert(leaseInfo);
//        LeaseInfo insertedLeaseInfo = leaseInfoDao.get(insertedId);
//
//        assertThat(insertedLeaseInfo, is(leaseInfo));
//
//        leaseInfo.setId(insertedId);
//        leaseInfo.setReturnSite("제주시");
//        leaseInfo.setReturnDay("1234-12-12");
//
//        leaseInfoDao.update(leaseInfo);
//
//        LeaseInfo updatedLeaseInfo = leaseInfoDao.get(leaseInfo.getId());
//
//        assertThat(leaseInfo, is(updatedLeaseInfo));
//
//    }
//
//}
