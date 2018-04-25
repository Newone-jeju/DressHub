package com.newoneplus.dresshub;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class Product {
    Integer id;
    String name;
    String thumbnailImage;
    String contents;
    Integer costPerDay;
    Integer deposit;
    Integer salePrice;
    String category;
    Date consigmentStart;
    Date consigmentEnd;
    String state;
    String deleveryType;
    String providerId;
    Integer likes;
    Date regDate;
    Integer leastLeaseDay;
    String size;

    @Override
    public boolean equals(Object o){
        boolean isEqual = true;

        //아이디 빼고 모든 자료형..
        if (o instanceof Product){
            if(!name.equals(((Product) o).getName())){
                isEqual = false;
            }else if(!thumbnailImage.equals(((Product) o).getThumbnailImage())){
                isEqual = false;
            }else if(!contents.equals(((Product) o).getContents())){
                isEqual = false;
            }else if(!costPerDay.equals(((Product) o).getCostPerDay())){
                isEqual = false;
            }else if(!deposit.equals(((Product) o).getDeposit())){
                isEqual = false;
            }else if(!salePrice.equals(((Product) o).getSalePrice())){
                isEqual = false;
            }else if(!category.equals(((Product) o).getCategory())){
                isEqual = false;
            }else if(!consigmentStart.equals(((Product) o).getConsigmentStart())){
                isEqual = false;
            }else if(!consigmentEnd.equals(((Product) o).getConsigmentEnd())){
                isEqual = false;
            }else if(!state.equals(((Product) o).getState())){
                isEqual = false;
            }else if(!deleveryType.equals(((Product) o).getDeleveryType())){
                isEqual = false;
            }else if(!providerId.equals(((Product) o).getProviderId())){
                isEqual = false;
            }else if(!likes.equals(((Product) o).getLikes())){
                isEqual = false;
            }else if(!regDate.equals(((Product) o).getRegDate())){
                isEqual = false;
            }else if(!leastLeaseDay.equals(((Product) o).getLeastLeaseDay())){
                isEqual = false;
            }else if(!size.equals(((Product) o).getSize())){
                isEqual = false;
            }
        }
        else{
            isEqual = false;
        }
        return isEqual;
    }

}

