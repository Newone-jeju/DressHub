package com.newoneplus.dresshub;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    int id;
    String name;
    String imageUrl;
    String contents;
    int costPerDay;
    int deposit;
    int salePrice;
    String category;
    Date consigmentStart;
    Date consigmentEnd;
    String state;
    String deleveryType;
    String providerId;
}
