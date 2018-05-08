package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.Review;
import com.newoneplus.dresshub.Service.ReviewService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ReviewContoller {
    @Autowired
    private ReviewService reviewService;

    //임시
    //TODO 유저로 오는거랑 상품으로 오는거랑 어떻게 구분하면 잘했다고 소문이 날까??
    @RequestMapping(value = "/review/{userId}", method = RequestMethod.GET)
    public String get(@PathVariable int productId){
        ArrayList<Review> reviews = reviewService.reviewsByProduct(productId);
        JSONArray jsonArray = new JSONArray();
        for(Review r : reviews){
            JSONObject jsonObject = new JSONObject();
            jsonObject.append("id", r.getId());
            jsonObject.append("title", r.getTitle());
            jsonObject.append("comment", r.getComment());
            jsonObject.append("rate", r.getRate());
            jsonObject.append("date", r.getDate());
            jsonObject.append("userId", r.getUserId());
            jsonObject.append("productId", r.getProductId());
            jsonObject.append("leaseStart", r.getLeaseStart());
            jsonObject.append("leaseEnd", r.getLeaseEnd());
            jsonObject.append("imageUrl", r.getImageUrl());
            jsonObject.append("id", r.getId());
            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }
}
