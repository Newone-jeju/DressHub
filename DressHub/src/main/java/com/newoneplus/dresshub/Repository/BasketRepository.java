package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.Basket;
import com.newoneplus.dresshub.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
        Page<Basket> findAllByHolder(String user, Pageable pageable);
}
