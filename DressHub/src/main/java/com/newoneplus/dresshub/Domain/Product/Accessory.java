package com.newoneplus.dresshub.Domain.Product;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Accessory extends Product{
    private String size;
    private Boolean isGold;
}
