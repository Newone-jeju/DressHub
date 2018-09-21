package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.Exceptions.*;
import com.newoneplus.dresshub.Model.Review;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    Review get(Integer id);
    List<Review> searchByProductId(Integer productId);
    List<Review> searchByuserId(String userId);
    Review insert(Review review) throws NotLoginedException, NoLeaseInfoException;
    Review update(Review review) throws NotLoginedException, NoResourcePresentException;
    void delete(Integer id) throws NotLoginedException, NoResourcePresentException, NoPermissionException;
    void saveImage(Integer reviewId, MultipartFile image, String token) throws NoResourcePresentException, DuplicateFileNameException, IOException;
}
