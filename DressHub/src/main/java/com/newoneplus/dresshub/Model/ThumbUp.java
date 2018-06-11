package com.newoneplus.dresshub.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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
