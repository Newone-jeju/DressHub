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


    @GetMapping("/list")
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
    public ResultMessage insert(@RequestBody Review review) {
        //TODO 테스트코드, 개발완료시 newReview사용할것
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

    private static final String IMAGE_PATH = System.getProperty("user.dir") + "/src/main/resources/static/review";

    @PostMapping("/{id}/image")
    @ResponseBody
    public ResultMessage insertImage(@RequestParam MultipartFile Image, @PathVariable Integer id) {
        //TODO 권한 문제  + 수정관련 이슈 해결필요
        ImageProcesser imageProcesser = new ImageProcesser();

        try {
            BufferedImage image = imageProcesser.getOriginImage(Image.getInputStream());
            ImageIO.write(image, "jpg", new File(IMAGE_PATH + "/" + id + "/image"));
        } catch (InvalidPathException e) {
            try {
                File reviewIdFolder = new File(IMAGE_PATH + "/" + id);
                reviewIdFolder.mkdir();
                BufferedImage image = imageProcesser.getOriginImage(Image.getInputStream());
                ImageIO.write(image, "jpg", new File(IMAGE_PATH + "/" + id + "/image"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(200);
        resultMessage.setMessage("accepted");

        return resultMessage;
    }

    @PutMapping("/{id}/image")
    @ResponseBody
    public ResultMessage updateImage(@RequestParam MultipartFile Image, @PathVariable Integer id) {
        ImageProcesser imageProcesser = new ImageProcesser();


        //TODO 권한 문제 해결 필요
        try {
            BufferedImage image = imageProcesser.getOriginImage(Image.getInputStream());
            ImageIO.write(image, "jpg", new File(IMAGE_PATH + "/" + id + "/image"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(200);
        resultMessage.setMessage("accepted");

        return resultMessage;
    }


}

