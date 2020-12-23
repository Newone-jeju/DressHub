package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.ImageProcesser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ReviewService {

    public String saveImageAndGetUrl(MultipartFile reviewImage) {
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

}
