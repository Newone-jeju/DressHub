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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
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
    public List<Review> searchByProductId(Integer productId) {
        return reviewRepository.findAllByProductId(productId);
    }

    @Override
    public List<Review> searchByuserId(String userId) {
        return reviewRepository.findAllByUser(userId);
    }


    @Override
    public Review insert(Review review) throws NoLeaseInfoException {
        HttpServletResponse res = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = req.getHeader("token");
        review.setId(null);

        AtomicReference<Review> result = null;

        Integer product = review.getProductId();
        List<LeaseInfo> leaseInfos = leaseInfoRepository.findAllByLeaserAndProduct(review.getUser(), product);

        if(leaseInfos.size() >= 1 ) {
            AuthContext.askAuthorityAndAct(review.getUser(), token, res, ()->{
                LeaseInfo leaseInfo = leaseInfos.get(leaseInfos.size() - 1);
                review.setUser(AuthorizationService.getCurrentUser().getUid());
                review.setLeaseStart(leaseInfo.getLeaseStart());
                review.setLeaseEnd(leaseInfo.getLeaseEnd());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                review.setDate(dateFormat.format(new Date()));
                result.set(reviewRepository.save(review));
            });
            return result.get();
        }else{
            throw new NoLeaseInfoException();
        }
    }

    @Override
    public Review update(Review review) throws NoResourcePresentException{
        HttpServletResponse res = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = req.getHeader("token");
        if (reviewRepository.existsById(review.getId())){
            review = reviewRepository.findById(review.getId()).get();
            AtomicReference<Review> result = null;
            Review finalReview = review;

            AuthContext.askAuthorityAndAct(review.getUser(), token, res, ()->{
                result.set(reviewRepository.save(finalReview));
            });
            return result.get();
        }else{
            throw new NoResourcePresentException();
        }
    }

    @Override
    public void delete(Integer id) throws NotLoginedException, NoResourcePresentException, NoPermissionException {
        //TODO 지금은 중복코드이나 곧 토큰이 사용될거라서 리팩토링은 일단 미룸
        String uid;
        try {
            uid = AuthorizationService.getCurrentUser().getUid();
        }catch (NullPointerException e){
            throw new NotLoginedException();
        }
        Optional<Review> review = reviewRepository.findById(id);
        if(!review.isPresent()){
            throw new NoResourcePresentException();
        }else if(!review.get().getUser().equals(uid)){
            throw new NoPermissionException();
        }
        reviewRepository.deleteById(id);

    }

    @Override
    public void saveImage(Integer reviewId, @RequestParam MultipartFile image, String token) throws IOException, NoResourcePresentException {
        BufferedImage bufferedImage = null;
        if(reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).get();
            String userInToken = "";
            if(review.getUser().equals(userInToken)) {
                InputStream inputStream = image.getInputStream();
                bufferedImage = ImageIO.read(inputStream);
                ImageIO.write(bufferedImage, "jpg", new File(IMAGE_PATH + "/" + image.getOriginalFilename()));
                inputStream.close();
            }else {
                throw new NoResourcePresentException();
            }
        }else{
            throw new NoResourcePresentException();
        }
        //TODO 이렇게만 하면 reviewId만 가지고도 악의적으로 사진을 업로드 할 수있는데... html form방식으로는 토큰을 헤더에 보낼수도 없고
        //결과적으로 권한문제를 해결해야함

    }
}
