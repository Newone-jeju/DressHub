package com.newoneplus.dresshub.Model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class ThumbUp{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    String userId;
    Long productId;
//    @ManyToOne
//    @JoinColumn(name="product_id")
//    Product product;
}
