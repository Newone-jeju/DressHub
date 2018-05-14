package com.newoneplus.dresshub.Model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
public class ReviewTest {
    private ReviewDao reviewDao;

    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        reviewDao = (ReviewDao) applicationContext.getBean("reviewDao");
    }

    @Test
    public void get(){
        Review review = new Review();
        int id = 0;
        review.setTitle("제목");
        review.setComment("내용");
        review.setRate(4.5f);
        review.setUserId("testuser");
        review.setProductId(0);
        review.setDate("2000-11-22 00:00:00.0"); //미리초까지 적어줘야함
        review.setLeaseStart("2000-11-22");
        review.setLeaseEnd("2000-11-22");
        review.setImageUrl("이미지경로");

        Review returnedReireview = reviewDao.get(id);

    }


    @Test
    public void getByUser(){
        Review review = new Review();
        String userId = "testuser";
        ArrayList<Review> reviews = reviewDao.getByUser(userId);
        Review returnedReview = reviews.get(0);

        review.setTitle("제목");
        review.setComment("내용");
        review.setRate(4.5f);
        review.setUserId("testuser");
        review.setProductId(0);
        review.setDate("2000-11-22 00:00:00.0"); //미리초까지 적어줘야함
        review.setLeaseStart("2000-11-22");
        review.setLeaseEnd("2000-11-22");
        review.setImageUrl("이미지경로");

        assertThat(returnedReview, is(review));
    }

    @Test
    public void getByProduct(){
        Review review = new Review();
        int productId = 0;
        ArrayList<Review> reviews = reviewDao.getByProduct(productId);
        Review returnedReview = reviews.get(0);

        review.setTitle("제목");
        review.setComment("내용");
        review.setRate(4.5f);
        review.setUserId("testuser");
        review.setProductId(0);
        review.setDate("2000-11-22 00:00:00.0"); //미리초까지 적어줘야함
        review.setLeaseStart("2000-11-22");
        review.setLeaseEnd("2000-11-22");
        review.setImageUrl("이미지경로");

        assertThat(returnedReview, is(review));
    }

    @Test
    public void insertUpdateDelete(){
        Review review = new Review();
        review.setTitle("제목");
        review.setComment("내용");
        review.setRate(4.5f);
        review.setUserId("testuser");
        review.setProductId(0);
        review.setDate("2000-11-22 00:00:00.0"); //미리초까지 적어줘야함
        review.setLeaseStart("2000-11-22");
        review.setLeaseEnd("2000-11-22");
        review.setImageUrl("이미지경로");

        int insertedId = reviewDao.insert(review);
        Review insertedReview = reviewDao.get(insertedId);

        assertThat(review, is(insertedReview));
        //방금 insert한거 수정
        Review reviewForUpdate = new Review();
        reviewForUpdate.setId(insertedId);
        reviewForUpdate.setTitle("수정된 제목");
        reviewForUpdate.setComment("수정된 내용");
        reviewForUpdate.setRate(4.3f);
        reviewForUpdate.setUserId("testuser");
        reviewForUpdate.setProductId(0);
        reviewForUpdate.setDate("2001-12-23 12:12:12.0"); //미리초까지 적어줘야함
        reviewForUpdate.setLeaseStart("2001-12-23");
        reviewForUpdate.setLeaseEnd("2001-12-23");
        reviewForUpdate.setImageUrl("수정된 이미지경로");

        reviewDao.update(reviewForUpdate);
        Review updatedReivew = reviewDao.get(insertedId);
        assertThat(reviewForUpdate, is(updatedReivew));

        //방금 넣고 수정한거 삭제
        reviewDao.delete(insertedId);

        Review deletedReview = reviewDao.get(insertedId);
        assertThat(deletedReview , is(nullValue()));
    }
}
