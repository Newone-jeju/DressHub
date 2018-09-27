package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findById(int id);
    Page<Review> findAllByUser(String user, Pageable pageable);
    Page<Review> findAllByProductId(int productId, Pageable pageable);
    void deleteById(int id);
    Review save(Review review);
}
