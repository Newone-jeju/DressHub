package com.newoneplus.dresshub.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GeneratorType;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String thumbnailImage;
    String contents;
    Integer costPerDay;
    Integer deposit;
    Integer salePrice;
    String location;
    String category;
    String consigmentStart;
    String consigmentEnd;
    String state;
    String deleveryType;
    String provider;
    Integer likes;
    Date registrationDate;
    Integer leastLeaseDay;
    String size;
    Integer searchPriority;
    @Transient
    ArrayList<MultipartFile> image;


}

