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

public interface ThumbUpRepository extends JpaRepository<ThumbUp, Integer>{

    @Query("delete from ThumbUp t where t.liker = :user and t.product= :product")
    @Modifying
    @Transactional
    void deleteByUidAndProduct(@Param("user") String user, @Param("product") Product product);
    List<ThumbUp> findAllByLiker(String user);

//    long countByUser(String user);
//    @Query(value = "select u from ThumbUp u join u.comment c where u.name = :name and u.password = :password")
//    Page<Product> findAllByNameAndPassword(@Param("name") String name, @Param("password") String password, Pageable pageable);
}
