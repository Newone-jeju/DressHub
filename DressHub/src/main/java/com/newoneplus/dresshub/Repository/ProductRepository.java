package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //좋아요한 product list 가져오기
    @Query(value = "select p from Product p where id in(:productlist)")
    List<Product> findAllByProductList(@Param("productlist") List<Integer> productlist);
    //Category에 따른 product list 가져오기
    Page<Product> findAllByCategoryContaining(String category, Pageable pageable);
    //그냥 리스트 가져오기
    List<Product> findAllByOrderByIdDesc();
    //제공자의 product list 가져오기
    Page<Product> findAllByProvider(String provider, Pageable pageable);
    List<Product> findAllByName(String name, Pageable pageable);
    Page<Product> findByIdIn(List<Integer> ids, Pageable pageable);
}
