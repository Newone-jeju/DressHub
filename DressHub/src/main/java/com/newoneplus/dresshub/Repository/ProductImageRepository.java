package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
     List<ProductImage> findAllByProductIdOrderByIdDesc(long productId);
     List<ProductImage> findAllByOrderByIdDesc();

}
