package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.Model.Review;
//import com.newoneplus.dresshub.Model.User;
//import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Repository.ReviewRepository;
import com.newoneplus.dresshub.Service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller("/review")
@ResponseBody
public class ReviewContoller{
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewService reviewService;

    @GetMapping("/{id}")
    public Optional get(@PathVariable Integer id){
        return reviewRepository.findById(id);
    }



    @GetMapping("/list")
    public List get(@RequestParam(defaultValue = "-1") Integer productId,
                      @RequestParam(defaultValue = "null") String userId) {
        List<Review> result = null;
        if (!productId.equals("null")) {
            result = reviewRepository.findAllByProduct(productId);
        } else if (!userId.equals("null")){
            result = reviewRepository.findAllByUser(userId);
        }
        return result;
    }


    @PostMapping
    public ResultMessage insert(@RequestBody Review review) {
        //TODO 테스트코드, 개발완료시 newReview사용할것
        String iamgeUrl = reviewService.saveImageAndGetUrl(review.getImage());
        review.setImageUrl(iamgeUrl);
        reviewRepository.save(review);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(200);
        resultMessage.setMessage("accepted");
        return resultMessage;
    }


    @DeleteMapping(value = "/{id}")
    public ResultMessage delete(@RequestParam Integer id) {
        reviewRepository.deleteById(id);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(200);
        resultMessage.setMessage("accepted");


        return resultMessage;
    }

    @PutMapping
    public ResultMessage update(@RequestBody Review review) {
        reviewRepository.save(review);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(200);
        resultMessage.setMessage("accepted");
        
        return resultMessage;
    }

//    @RequestMapping(value = "/review-form.html", method = RequestMethod.GET)
//    public String updateAuthentication(){
////        User user = AuthorizationService.getCurrentUser();
////        if(user==null) {
////            return "redirect:/login";
////        }else {
////            return "redirect:/review-form.html";
////        }
//    }
}

