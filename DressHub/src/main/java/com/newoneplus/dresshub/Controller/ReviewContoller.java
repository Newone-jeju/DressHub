package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.Review;
import com.newoneplus.dresshub.Service.ReviewService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Controller
public class ReviewContoller {
    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/review", method = RequestMethod.GET)
    @ResponseBody
    public String get(@RequestParam(defaultValue = "null") String productId, @RequestParam(defaultValue = "null") String userId) {
        String reviewsJsonString = null;
        if (!productId.equals("null")) {
            reviewsJsonString = reviewService.getReviewsJsonStringFormByProduct(Integer.parseInt(productId));
        } else if (!userId.equals("null")) {
            reviewsJsonString = reviewService.getReviewsJsonStringFormByUser(userId);
        }
        return reviewsJsonString;
    }


    @RequestMapping(value = "/review/new", method = RequestMethod.POST)
    public String insert(@ModelAttribute Review review) {
//        (@RequestParam String title, @RequestParam String comment, @RequestParam String rate,
//                @RequestParam String userId, @RequestParam String productId + 이미지


        //TODO 테스트코드, 개발완료시 newReview사용할것
        reviewService.newReviewTest(review);
        return null;
    }

    //권한 점검 필요

    @RequestMapping(value = "/review/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam String id) {
        int idForReview = Integer.parseInt(id);
        reviewService.delete(idForReview);
        return null;
    }

    @RequestMapping(value = "/reveiw/update", method = RequestMethod.PUT)
    public String update(@ModelAttribute Review review) {
//        @RequestParam String id, @RequestParam String title, @RequestParam  String comment,
//        @RequestParam String rate + 이미지

        reviewService.update(review);
        return null;
    }
}


