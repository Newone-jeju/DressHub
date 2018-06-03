package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findById(int id);
    List<Review> findAllByUser(String user);
    List<Review> findAllByProductId(int productId);
    void deleteById(int id);
    Review save(Review review);
}
