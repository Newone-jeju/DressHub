package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository {
    List<Review> findAllById(int id);
    List<Review> findAllByUser(String user);
    List<Review> findAllByProduct(int product);
    void deleteById(int id);
    Review save(Review review);
}
