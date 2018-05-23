package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.Review;
import com.newoneplus.dresshub.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewContoller {
    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/review", method = RequestMethod.GET)
    @ResponseBody
    public String get(@RequestParam(defaultValue = "null") String productId,
                      @RequestParam(defaultValue = "null") String userId,
                      @RequestParam(defaultValue = "null") String id) {
        String reviewsJsonString = null;
        if(!id.equals("null")) {
            reviewsJsonString = reviewService.getReviewByIdToJson(Integer.parseInt(id));
        }else if (!productId.equals("null")) {
            reviewsJsonString = reviewService.getReviewsByProductToJson(Integer.parseInt(productId));
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

    @RequestMapping(value = "/review/delete", method = RequestMethod.POST)
    public String delete(@RequestParam String id) {
        int idForReview = Integer.parseInt(id);
        reviewService.delete(idForReview);
        return "redirect:/product_details.html";
    }

    @RequestMapping(value = "/review/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Review review) {

        reviewService.update(review);
        return null;
    }
}

