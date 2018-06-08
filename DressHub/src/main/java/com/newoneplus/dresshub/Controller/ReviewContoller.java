package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.Model.Review;
//import com.newoneplus.dresshub.Model.User;
//import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Repository.ReviewRepository;
import com.newoneplus.dresshub.Service.ReviewService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review")
@ResponseBody
@AllArgsConstructor
public class ReviewContoller {
    ReviewRepository reviewRepository;
    ReviewService reviewService;

    @GetMapping("/{id}")
    public Optional get(@PathVariable Integer id) {
        return reviewRepository.findById(id);
    }


    @GetMapping("/list/search")
    public List get(@RequestParam(defaultValue = "-1") Integer productId,
                    @RequestParam(defaultValue = "null") String userId) {
        List<Review> result = null;
        if (!productId.equals("null")) {
            result = reviewRepository.findAllByProductId(productId);
        } else if (!userId.equals("null")) {
            result = reviewRepository.findAllByUser(userId);
        }
        return result;
    }

    @PostMapping
    public ResultMessage insert(@RequestBody Review review, HttpServletResponse res) {
        //TODO 테스트코드, 개발완료시 newReview사용할것
        reviewRepository.save(review);
        res.setStatus(200);
        return null;
    }


    @DeleteMapping(value = "/{id}")
    public ResultMessage delete(@RequestParam Integer id, HttpServletResponse res) {

        reviewRepository.deleteById(id);
        res.setStatus(200);
        return null;

    }


    @PutMapping
    public ResultMessage update(@RequestBody Review review, HttpServletResponse res) {
        reviewRepository.save(review);
        res.setStatus(200);
        return null;
    }

    private static final String IMAGE_PATH = System.getProperty("user.dir") + "/out/production/main/resources/static/review/image.";

    @PostMapping("/image")
    @ResponseBody
    public ResultMessage insertImage(@RequestParam MultipartFile image, HttpServletResponse res) {
        //TODO 권한 문제  + 수정관련 이슈 해결필요
        saveImage(image);
        res.setStatus(200);
        return null;
    }

    @PutMapping("/image")
    @ResponseBody
    //TODO 권한 점검 필요
    public ResultMessage updateImage(@RequestParam MultipartFile image, HttpServletResponse res) {
        saveImage(image);
        res.setStatus(200);

        return null;
    }

    private void saveImage(@RequestParam MultipartFile image) {
        ImageProcesser imageProcesser = new ImageProcesser();
        //TODO 권한 문제 해결 필요
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = imageProcesser.getOriginImage(image.getInputStream());
            ImageIO.write(bufferedImage, "jpg", new File(IMAGE_PATH + "/" + image.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

