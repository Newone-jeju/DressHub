package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.Basket;
import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ThumbUp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
        Page<Basket> findAllByHolder(String user, Pageable pageable);
        @Query(value = "select b from Basket b where b.holder like :holder and b.product in(:productList)  ")
        List<Basket> findAllByHolderAndProductList(String holder, List<Integer> productList);
        Optional<Basket> findByHolderAndProduct(String holder, Integer product);
}
