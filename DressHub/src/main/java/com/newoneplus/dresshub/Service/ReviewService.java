package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.Exceptions.*;
import com.newoneplus.dresshub.Model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    Review get(Integer id);
    Page<Review> searchByProductId(Integer productId, Pageable pageable);
    Page<Review> searchByuserId(String userId, Pageable pageable);
    Review insert(Review review) throws NoLeaseInfoException;
    Review update(Review review) throws NoResourcePresentException;
    void delete(Integer id) throws NoResourcePresentException;
    void saveImage(Integer reviewId, MultipartFile image, String token) throws NoResourcePresentException;
}
