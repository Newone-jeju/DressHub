package com.newoneplus.dresshub.Domain.Product;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
public class Clothes extends Product{
    private String size;
    private String brand;
}
