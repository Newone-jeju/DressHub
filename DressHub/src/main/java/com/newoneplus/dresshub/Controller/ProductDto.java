package com.newoneplus.dresshub.Controller;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
public class ProductDto {
    Long id;
    String name;
    String thumbnailImage;
    String contents;
    Integer costPerDay;
    Integer deposit;
    Integer salePrice;
    String location;
    String consigmentStart;
    String consigmentEnd;
    String state;
    String deleveryType;
    String provider;
    Date registrationDate;
    Integer leastLeaseDay;
    String size;
}
