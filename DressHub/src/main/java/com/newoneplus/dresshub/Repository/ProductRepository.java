package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //    이렇게 하면 안된다!!
    @Query(value = "select p from Product p inner join ThumbUp t on (p.id = t.product)and (t.liker = :user) ")
    Page<Product> findAllByUid(@Param("user")String user, Pageable pageable);
    @Query(value="select p from Product p left outer join ThumbUp t on (p.id=t.product) and t.liker =:user where category like :category%")
    Page<Product> findAllByCatetoryJoinThumbUpByUid(@Param("category")String category, @Param("user")String user ,Pageable pageable);
    Product findById(long id);
    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByProvider(String provider);
}
