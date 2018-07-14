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

    //유저의 좋아요 리스트  변경 (1. user의 좋아요목록만 가져온다. 그거를 front에서 받아서 ajax로 2. product요청) 여기서 1번은 thumbup으로 위임 2번은 여기다가
    @Query(value = "select p from Product p where id in(:productlist)")
    List<Product> findAllByThumbUpList(@Param("productlist") List<Integer> productlist);

    @Query(value="select p from Product p left outer join ThumbUp t on (p.id=t.product) and t.liker =:user where category like :category%")
    Page<Product> findAllByCatetoryJoinThumbUpByUid(@Param("category")String category, @Param("user")String user ,Pageable pageable);



    Product findById(long id);
    List<Product> findAllByOrderByIdDesc();
    List<Product> findAllByProvider(String provider);
}
