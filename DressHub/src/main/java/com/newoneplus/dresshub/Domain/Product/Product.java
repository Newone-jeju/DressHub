package com.newoneplus.dresshub.Domain.Product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newoneplus.dresshub.Domain.Category;
import com.newoneplus.dresshub.Domain.ThumbUp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="dtype")
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
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
    Integer likes;
    Date registrationDate;
    Integer leastLeaseDay;
    String size;
    @ManyToMany(mappedBy = "products")
    private List<Category> categories = new ArrayList<>();
    @Transient
    ArrayList<MultipartFile> image;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnoreProperties(value = "product", allowSetters = true)
    List<ThumbUp> thumbUps;

//    @Override
//    public boolean equals(Object o){
//        boolean isEqual = true;
//
//        //아이디 빼고 모든 자료형..
//        if (o instanceof Product){
//            if(!name.equals(((Product) o).getName())){
//                isEqual = false;
//            }else if(!thumbnailImage.equals(((Product) o).getThumbnailImage())){
//                isEqual = false;
//            }else if(!contents.equals(((Product) o).getContents())){
//                isEqual = false;
//            }else if(!costPerDay.equals(((Product) o).getCostPerDay())){
//                isEqual = false;
//            }else if(!deposit.equals(((Product) o).getDeposit())){
//                isEqual = false;
//            }else if(!salePrice.equals(((Product) o).getSalePrice())){
//                isEqual = false;
//            }else if(!category.equals(((Product) o).getCategory())){
//                isEqual = false;
//            }else if(!consigmentStart.equals(((Product) o).getConsigmentStart())){
//                isEqual = false;
//            }else if(!consigmentEnd.equals(((Product) o).getConsigmentEnd())){
//                isEqual = false;
//            }else if(!state.equals(((Product) o).getState())){
//                isEqual = false;
//            }else if(!deleveryType.equals(((Product) o).getDeleveryType())){
//                isEqual = false;
//            }else if(!provider.equals(((Product) o).getProvider())){
//                isEqual = false;
//            }else if(!likes.equals(((Product) o).getLikes())){
//                isEqual = false;
//            }else if(!registrationDate.equals(((Product) o).getRegistrationDate())){
//                isEqual = false;
//            }else if(!leastLeaseDay.equals(((Product) o).getLeastLeaseDay())){
//                isEqual = false;
//            }else if(!size.equals(((Product) o).getSize())){
//                isEqual = false;
//            }
//        }
//        else{
//            isEqual = false;
//        }
//        return isEqual;
//    }
}

