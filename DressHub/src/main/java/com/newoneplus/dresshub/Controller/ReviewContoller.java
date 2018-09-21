package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Exceptions.*;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.Model.Review;
import com.newoneplus.dresshub.Service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/review")
@ResponseBody
public class ReviewContoller {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public Review get(@PathVariable Integer id) {
        return reviewService.get(id);
    }


    //TODO 이거 그냥 추가 url없이 하면 좋을것같음
    @GetMapping("/list/search")
    public List get(@RequestParam(defaultValue = "-1") Integer productId,
                    @RequestParam(defaultValue = "null") String userId, HttpServletResponse res) {
        List<Review> result = null;
        if (!productId.equals("null")) {
            result = reviewService.searchByProductId(productId);
        } else if (!userId.equals("null")) {
            result = reviewService.searchByuserId(userId);
        } else {
            res.setStatus(400, "잘못된 요청입니다.");
        }
        return result;
    }

    @PostMapping
    public Review insert(@RequestBody Review review, HttpServletResponse res) {
        Review result = null;
        try {
            result = reviewService.insert(review);
        } catch (NoLeaseInfoException e) {
            res.setStatus(403, e.getMessage());
        }
        return result;
    }


    @DeleteMapping(value = "/{id}")
    public ResultMessage delete(@RequestParam Integer id, HttpServletResponse res) {
        try {
            reviewService.delete(id);
        } catch (NoResourcePresentException e) {
            res.setStatus(404, e.getMessage());
        }
        return null;
    }


    @PutMapping
    public Review update(@RequestBody Review review, HttpServletResponse res) {
        Review result = null;
        try {
            result = reviewService.update(review);
        } catch (NoResourcePresentException e) {
            res.setStatus(404, e.getMessage());
        }
        return result;
    }


    @PostMapping("/image")
    @ResponseBody
    public ResultMessage insertImage(@RequestParam MultipartFile image, @RequestParam Integer reviewId, HttpServletResponse res) {
        try {
            reviewService.saveImage(reviewId, image, "");
        } catch (NoResourcePresentException e) {
            res.setStatus(404, e.getMessage());
        } catch (DuplicateFileNameException e) {
            res.setStatus(400, e.getMessage());
        }
        return null;
    }


}

