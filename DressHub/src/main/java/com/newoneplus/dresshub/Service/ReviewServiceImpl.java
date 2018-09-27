package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.Controller.AuthContext;
import com.newoneplus.dresshub.Exceptions.NoLeaseInfoException;
import com.newoneplus.dresshub.Exceptions.NoPermissionException;
import com.newoneplus.dresshub.Exceptions.NoResourcePresentException;
import com.newoneplus.dresshub.Exceptions.NotLoginedException;
import com.newoneplus.dresshub.Model.LeaseInfo;
import com.newoneplus.dresshub.Model.Review;
import com.newoneplus.dresshub.Repository.LeaseInfoRepository;
import com.newoneplus.dresshub.Repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service(value = "ReviewServiceImpl")
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private static final String IMAGE_PATH = System.getProperty("user.dir") + "/out/production/resources/static/review/image/";

    ReviewRepository reviewRepository;
    LeaseInfoRepository leaseInfoRepository;
    @Override
    public Review get(Integer id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
    }

    @Override
    public Page<Review> searchByProductId(Integer productId, Pageable pageable) {
        return reviewRepository.findAllByProductId(productId, pageable);
    }

    @Override
    public Page<Review> searchByuserId(String userId, Pageable pageable) {
        return reviewRepository.findAllByUser(userId, pageable);
    }


    @Override
    public Review insert(Review review) throws NoLeaseInfoException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = req.getHeader("token");
        review.setId(null);
        String userId = (String) AuthContext.getDecodedToken(token).get("uid");

        Integer product = review.getProductId();
        List<LeaseInfo> leaseInfos = leaseInfoRepository.findAllByLeaserAndProduct(userId, product);
        if(leaseInfos.size() >= 1 ) {
            final Review[] result = {null};
            AuthContext.askLoginedAndAct(token, ()->{
                LeaseInfo leaseInfo = leaseInfos.get(leaseInfos.size() - 1);
                review.setUser((String) AuthContext.getDecodedToken(token).get("uid"));
                review.setLeaseStart(leaseInfo.getLeaseStart());
                review.setLeaseEnd(leaseInfo.getLeaseEnd());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                review.setDate(dateFormat.format(new Date()));
                result[0] = reviewRepository.save(review);
            });
            return result[0];
        }else{
            throw new NoLeaseInfoException();
        }
    }

    @Override
    public Review update(Review review) throws NoResourcePresentException{
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = req.getHeader("token");
        if (reviewRepository.existsById(review.getId())){
            review = reviewRepository.findById(review.getId()).get();
            final Review[] result = {null};
            Review finalReview = review;

            AuthContext.askAuthorityAndAct(review.getUser(), token,()->{
                result[0] = reviewRepository.save(finalReview);
            });
            return result[0];
        }else{
            throw new NoResourcePresentException();
        }
    }

    @Override
    public void delete(Integer id) throws NoResourcePresentException{
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = req.getHeader("token");

        if(reviewRepository.existsById(id)){
            Review review = reviewRepository.findById(id).get();
            AuthContext.askAuthorityAndAct(review.getUser(), token, ()->{
                reviewRepository.deleteById(id);
            });
        }else{
            throw new NoResourcePresentException();
        }
    }

    @Override
    public void saveImage(Integer reviewId, MultipartFile image, String token) throws NoResourcePresentException{
        System.out.println(token);
        if(reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).get();
            AuthContext.askAuthorityAndAct(review.getUser(), token, ()->{
                //throw IOException
                InputStream inputStream = image.getInputStream();
                BufferedImage bufferedImage = ImageIO.read(inputStream);
                ImageIO.write(bufferedImage, "jpg", new File(IMAGE_PATH  + image.getOriginalFilename()));
                inputStream.close();
            });
        }else {
            throw new NoResourcePresentException();
        }

    }
}
