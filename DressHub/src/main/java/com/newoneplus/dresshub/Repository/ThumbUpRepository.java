package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ThumbUp;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ThumbUpRepository extends JpaRepository<ThumbUp, Integer>{
    Page<ThumbUp> findAllByLiker(String liker, Pageable pageable);
    @Query(value = "select t from ThumbUp t where t.liker like :liker and t.product in(:productList)  ")
    List<ThumbUp> findAllByLikerAndProductList(String liker, List<Integer> productList);
    Optional<ThumbUp> findByLikerAndProduct(String liker, Integer product);

}
