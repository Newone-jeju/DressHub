package com.newoneplus.dresshub.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newoneplus.dresshub.Domain.Product.Product;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ThumbUp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String uid;
    @ManyToOne
    @JsonIgnoreProperties(value = "thumbUp", allowSetters = true)
    @JoinColumn(name="product_id")
    Product product;

}
