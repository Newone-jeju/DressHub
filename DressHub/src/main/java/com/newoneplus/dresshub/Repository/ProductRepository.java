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

    @Query(value = "select p from Product p join ThumbUp t on (p.id=t.product) and t.user = :user")
    Page<Product> findAllByUser(@Param("user")String user, Pageable pageable);
    @Query(value="select p from Product p left outer join ThumbUp t on (p.id=t.product) and t.user =:user where category like :category%")
    Page<Product> findAllByCatetoryJoinThumbUpByUser(@Param("category")String category, @Param("user")String user ,Pageable pageable);
    Product findById(long id);
    List<Product> findAllByOrderByIdDesc();

}
