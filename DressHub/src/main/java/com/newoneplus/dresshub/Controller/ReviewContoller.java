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

@RestController
public class ReviewContoller {
    @Autowired
    private ReviewService reviewService;

    //임시
    //TODO 유저로 오는거랑 상품으로 오는거랑 어떻게 구분하면 잘했다고 소문이 날까??
    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public String get(@RequestParam (defaultValue = "null")String productId, @RequestParam(defaultValue = "null")String userId){
        ArrayList<Review> reviews = null;
        if (!productId.equals("null")) {
            reviews = reviewService.reviewsByProduct(Integer.parseInt(productId));
        }else if(!userId.equals("null")){
            reviews = reviewService.reviewsByUser(userId);
        }
        JSONArray jsonArray = new JSONArray();
        try {
            for (Review r : reviews) {
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
                jsonArray.put(jsonObject);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return jsonArray.toString();
    }

    @RequestMapping(value = "/review/new", method = RequestMethod.POST)
    public String insert(@ModelAttribute Review review){
//        (@RequestParam String title, @RequestParam String comment, @RequestParam String rate,
//                @RequestParam String userId, @RequestParam String productId + 이미지


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        review.setDate(today);

        //TODO 이거 세개는 우리가 가져와야 될 것 같은데
        String leaseStart = null;
        String leaseEnd = null;


        String filepath = saveImageAndGetPath(review.getImage());

        review.setLeaseEnd(leaseEnd);
        review.setLeaseStart(leaseStart);
        review.setImageUrl(filepath);

        reviewService.insert(review);
        return null;
    }

    //권한 점검 필요

    @RequestMapping(value = "review/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam String id){
        int idForReview = Integer.parseInt(id);
        reviewService.delete(idForReview);
        return null;
    }
    @RequestMapping(value = "reveiw/update", method = RequestMethod.PUT )
    public String update(@ModelAttribute Review review){
//        @RequestParam String id, @RequestParam String title, @RequestParam  String comment,
//        @RequestParam String rate + 이미지

        Review reviewForUpdate = reviewService.get(review.getId());

        reviewForUpdate.setTitle(review.getTitle());

        reviewForUpdate.setComment(review.getComment());
        if(review.getImage() != null) {
            String filepath = saveImageAndGetPath(review.getImage());
            reviewForUpdate.setImageUrl(filepath);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        reviewForUpdate.setDate(today);
        reviewForUpdate.setRate(review.getRate());

        reviewService.update(reviewForUpdate);
        return null;
    }

    private String saveImageAndGetPath(MultipartFile reviewImage) {
        String path = System.getProperty("user.dir") + "src/main/resouces/static/image/reivew";
        String filename = reviewImage.getOriginalFilename();

        ImageProcesser imageProcesser = new ImageProcesser();

        try {
            BufferedImage image = imageProcesser.getOriginImage(reviewImage.getInputStream());
            ImageIO.write(image,"jpg", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path + filename;
    }
}
