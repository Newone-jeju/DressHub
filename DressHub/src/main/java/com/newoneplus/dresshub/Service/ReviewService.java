package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.Model.Review;
import com.newoneplus.dresshub.Model.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ReviewService {

    //    productDao가 bean에 등록되어야 autowired를 쓸 수 있음
    @Autowired
    private ReviewDao reviewDao;

    public ArrayList<Review> reviewsByProduct(int productId){
        return reviewDao.getByProduct(productId);

    }

    public ArrayList<Review> reviewsByUser(String userId){
        return reviewDao.getByUser(userId);
    }

    public void update(Review review){
        reviewDao.update(review);
    }

    public void delete(int id){
        reviewDao.delete(id);
    }

}
