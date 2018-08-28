package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Exceptions.*;
import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.LeaseInfo;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.Model.Review;
//import com.newoneplus.dresshub.Model.User;
//import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Repository.LeaseInfoRepository;
import com.newoneplus.dresshub.Repository.ReviewRepository;
import com.newoneplus.dresshub.Service.AuthorizationService;

import com.newoneplus.dresshub.Service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequestMapping("/review")
@ResponseBody
public class ReviewContoller {
    @Qualifier("ReviewServiceImpl")
    ReviewService reviewService;
    @GetMapping("/{id}")
    public Review get(@PathVariable Integer id) {
        return reviewService.get(id);
    }


    @GetMapping("/list/search")
    public List get(@RequestParam(defaultValue = "-1") Integer productId,
                    @RequestParam(defaultValue = "null") String userId, HttpServletResponse res) {
        List<Review> result = null;
        if (!productId.equals("null")) {
            result = reviewService.searchByProductId(productId);
        } else if (!userId.equals("null")) {
            result = reviewService.searchByuserId(userId);
        }else{
            res.setStatus(400, "잘못된 요청입니다.");
        }
        return result;
    }

    @PostMapping
    public Review insert(@RequestBody Review review, HttpServletResponse res) {
        Review result = null;
        try {
            result = reviewService.insert(review);
        } catch (NotLoginedException e) {
            res.setStatus(401, e.getMessage());
        } catch (NoLeaseInfoException e) {
            res.setStatus(403, e.getMessage());
        }
        return result;
    }


    @DeleteMapping(value = "/{id}")
    public ResultMessage delete(@RequestParam Integer id, HttpServletResponse res) {
        try {
            reviewService.delete(id);
        } catch (NotLoginedException e) {
            res.setStatus(401, e.getMessage());
        } catch (NoResourcePresentException e) {
            res.setStatus(404, e.getMessage());
        } catch (NoPermissionException e) {
            res.setStatus(403, e.getMessage());
        }
        return null;
    }


    @PutMapping
    public Review update(@RequestBody Review review, HttpServletResponse res) {
        Review result = null;
        try {
            result = reviewService.update(review);
        } catch (NotLoginedException e) {
            res.setStatus(401, e.getMessage());
        } catch (NoResourcePresentException e) {
            res.setStatus(404, e.getMessage());
        }
        return result;
    }



    @PostMapping("/image")
    @ResponseBody
    public ResultMessage insertImage(@RequestParam MultipartFile image, @RequestParam Integer reviewId,HttpServletResponse res) {
        try {
            reviewService.saveImage(reviewId, image);
        } catch (NoResourcePresentException e) {
            res.setStatus(404, e.getMessage());
        } catch (DuplicateFileNameException e) {
            res.setStatus(400, e.getMessage());
        } catch (IOException e) {
            res.setStatus(500, "파일입출력 에러");
        }
        return null;
    }

}

