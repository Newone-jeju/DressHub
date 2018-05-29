package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.Model.Review;
import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
public class ReviewContoller{
    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/review", method = RequestMethod.GET)
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


    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public ResultMessage insert(@ModelAttribute Review review) {
        //TODO 테스트코드, 개발완료시 newReview사용할것
        reviewService.newReviewTest(review);

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(200);
        resultMessage.setMessage("accepted");
        return resultMessage;
    }


    @RequestMapping(value = "/review", method = RequestMethod.DELETE)
    public ResultMessage delete(@RequestParam String id) {
        int idForReview = Integer.parseInt(id);
        reviewService.delete(idForReview);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(200);
        resultMessage.setMessage("승인");
        return resultMessage;
    }

    @RequestMapping(value = "/review", method = RequestMethod.PUT)
    public ResultMessage update(@ModelAttribute Review review) {
        reviewService.update(review);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(200);
        resultMessage.setMessage("승인");
        return resultMessage;
    }

}

