package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Domain.Product.Product;
import com.newoneplus.dresshub.Domain.ThumbUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ThumbUpRepository extends JpaRepository<ThumbUp, Integer>{

    @Query("delete from ThumbUp t where t.uid = :user and t.product= :product")
    @Modifying
    @Transactional
    void deleteByUidAndProduct(@Param("user") String user, @Param("product") Product product);
    List<ThumbUp> findAllByUid(String user);

//    long countByUser(String user);
//    @Query(value = "select u from ThumbUp u join u.comment c where u.name = :name and u.password = :password")
//    Page<Product> findAllByNameAndPassword(@Param("name") String name, @Param("password") String password, Pageable pageable);
}
