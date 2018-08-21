package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.LeaseInfo;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.Model.Review;
//import com.newoneplus.dresshub.Model.User;
//import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Repository.LeaseInfoRepository;
import com.newoneplus.dresshub.Repository.ReviewRepository;
import com.newoneplus.dresshub.Service.AuthorizationService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review")
@ResponseBody
@AllArgsConstructor
public class ReviewContoller {
    ReviewRepository reviewRepository;
    LeaseInfoRepository leaseInfoRepository;

    @GetMapping("/{id}")
    public Review get(@PathVariable Integer id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
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
    public Review insert(@RequestBody Review review, HttpServletResponse res) {
        //TODO 로그인
        try {
            review.setUser(AuthorizationService.getCurrentUser().getUid());
        }
        catch (NullPointerException e ){
            res.setStatus(401, "로그인 안됨");
            return null;
        }
        Integer product = review.getProductId();
        List<LeaseInfo> leaseInfos = leaseInfoRepository.findAllByLeaserAndProduct(review.getUser(), product);
        if(leaseInfos.size() >= 1 ) {
            LeaseInfo leaseInfo = leaseInfos.get(leaseInfos.size() - 1);
            review.setLeaseStart(leaseInfo.getLeaseStart());
            review.setLeaseEnd(leaseInfo.getLeaseEnd());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            review.setDate(dateFormat.format(new Date()));
            //TODO 이미지 처리 보류
            return reviewRepository.save(review);
        }else{
            res.setStatus(403, "대여기록이 없음");
            return null;
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResultMessage delete(@RequestParam Integer id, HttpServletResponse res) {

        reviewRepository.deleteById(id);
        return null;
    }


    @PutMapping
    public Review update(@RequestBody Review review, HttpServletResponse res) {
        //TODO 시연용 유저 정보 가져오는 코드 반드시 지울것
        review.setUser(AuthorizationService.getCurrentUser().getUid());
        return reviewRepository.save(review);
    }

    private static final String IMAGE_PATH = System.getProperty("user.dir") + "/out/production/resources/static/review/image/";

    @PostMapping("/image")
    @ResponseBody
    public ResultMessage insertImage(@RequestParam MultipartFile image, HttpServletResponse res) {

        //TODO 권한 문제  + 수정관련 이슈 해결필요
        saveImage(image);
        return null;
    }

    @PutMapping("/image")
    @ResponseBody
    //TODO 권한 점검 필요
    public ResultMessage updateImage(@RequestParam MultipartFile image, HttpServletResponse res) {

        saveImage(image);

        return null;
    }

    private void saveImage(@RequestParam MultipartFile image) {
        ImageProcesser imageProcesser = new ImageProcesser();
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(image.getInputStream());
            ImageIO.write(bufferedImage, "jpg", new File(IMAGE_PATH + "/" + image.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

