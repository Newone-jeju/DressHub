package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Domain.Basket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
//    @Query(value="select b from Basket b left outer join Product p on (b.product=p.id) where b.uid =:member")
//    Page<Basket> findAllByMemberJoinProduct(@Param("member")String member , Pageable pageable);
}
