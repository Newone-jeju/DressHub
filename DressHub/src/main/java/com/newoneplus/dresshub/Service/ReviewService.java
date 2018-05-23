package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.LeaseInfo;
import com.newoneplus.dresshub.Model.LeaseInfoDao;
import com.newoneplus.dresshub.Model.Review;
import com.newoneplus.dresshub.Model.ReviewDao;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ReviewService {
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private LeaseInfoDao leaseInfoDao;

    public void update(Review review){
        Review reviewForUpdate = reviewDao.get(review.getId());

        reviewForUpdate.setTitle(review.getTitle());

        reviewForUpdate.setComment(review.getComment());
        try {
            String filepath = saveImageAndGetUrl(review.getImage());
            reviewForUpdate.setImageUrl(filepath);
        }catch (IllegalArgumentException e){
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        reviewForUpdate.setDate(today);
        reviewForUpdate.setRate(review.getRate());
        reviewDao.update(reviewForUpdate);
    }

    public void delete(int id){
        reviewDao.delete(id);
    }

    public void newReview(Review review) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        review.setDate(today);

        ArrayList<LeaseInfo> leaseInfos = leaseInfoDao.getInfosByUserAndProduct(review.getUserId(), review.getProductId());
        LeaseInfo leaseInfo = leaseInfos.get(leaseInfos.size() - 1);

        String leaseStart = leaseInfo.getLeaseDay();
        String leaseEnd = leaseInfo.getReturnDay();
        String filepath = saveImageAndGetUrl(review.getImage());

        review.setLeaseEnd(leaseEnd);
        review.setLeaseStart(leaseStart);
        review.setImageUrl(filepath);

        reviewDao.insert(review);
    }
    @Deprecated
    //TODO 테스트용 코드 반드시 지울것
    public void newReviewTest(Review review){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        review.setDate(today);

        String leaseEnd = "2000-01-01";
        String leaseStart = "2000-01-01";
        String filepath = saveImageAndGetUrl(review.getImage());

        review.setLeaseEnd(leaseEnd);
        review.setLeaseStart(leaseStart);
        review.setImageUrl(filepath);



        review.setProductId(0);
        review.setUserId("testuser");


        reviewDao.insert(review);
    }

    public String getReviewsByProductToJson(int productId) {
        ArrayList<Review> reviews = null;
        reviews = reviewDao.getByProduct(productId);
        JSONArray jsonArray = getReviewsJsonArray(reviews);

        return jsonArray.toString();
    }

    public String getReviewsJsonStringFormByUser(String userId) {
        ArrayList<Review> reviews = null;
        reviews = reviewDao.getByUser(userId);
        JSONArray jsonArray = getReviewsJsonArray(reviews);

        return jsonArray.toString();
    }

    private JSONArray getReviewsJsonArray(ArrayList<Review> reviews) {
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
        return jsonArray;
    }

    private String saveImageAndGetUrl(MultipartFile reviewImage) {
        String path = System.getProperty("user.dir") + "/src/main/resources/static/image/review/";
        String filename = reviewImage.getOriginalFilename();

        ImageProcesser imageProcesser = new ImageProcesser();

        try {
            BufferedImage image = imageProcesser.getOriginImage(reviewImage.getInputStream());
            ImageIO.write(image,"jpg", new File(path + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/image/review/" + filename;
    }

    public String getReviewByIdToJson(int id) {
        Review review = reviewDao.get(id);
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.append("id", review.getId());
        jsonObject.append("title", review.getTitle());
        jsonObject.append("comment", review.getComment());
        jsonObject.append("rate", review.getRate());
        jsonObject.append("date", review.getDate());
        jsonObject.append("userId", review.getUserId());
        jsonObject.append("productId", review.getProductId());
        jsonObject.append("leaseStart", review.getLeaseStart());
        jsonObject.append("leaseEnd", review.getLeaseEnd());
        jsonObject.append("imageUrl", review.getImageUrl());
        jsonArray.put(jsonObject);

        return jsonArray.toString();

    }
}
