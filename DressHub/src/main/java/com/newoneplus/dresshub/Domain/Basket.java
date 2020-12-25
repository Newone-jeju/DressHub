package com.newoneplus.dresshub.Domain;

import com.newoneplus.dresshub.Domain.Product.Product;
import lombok.Data;

import javax.persistence.*;

//일단은 이정도의 데이터가 필요할 것같음
@Data
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uid;

//    @ManyToOne
//    @JoinColumn(name="product_id")
//    private Product product;



}
