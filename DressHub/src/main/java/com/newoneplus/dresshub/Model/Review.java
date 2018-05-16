package com.newoneplus.dresshub.Model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Data
public class Review {
    Integer id;
    String title;
    String comment;
    Float rate;
    String date;  //0000-00-00 hh:mm:ss
    String userId;
    Integer productId;
    String leaseStart; //0000-00-00
    String leaseEnd; //0000-00-00
    String imageUrl;
    //로컬에 저장을 위한 이미지
    MultipartFile image;
    @Override
    public boolean equals(Object o){
        boolean isEqual = true;
        //아이디 빼고 모든 자료형..
        if (o instanceof Review){
            if(!title.equals(((Review) o).getTitle())){
                isEqual = false;
            }else if(!comment.equals(((Review) o).getComment())){
                isEqual = false;
            }else if(!rate.equals(((Review) o).getRate())){
                isEqual = false;
            }else if(!date.equals(((Review) o).getDate())){
                isEqual = false;
            }else if(!userId.equals(((Review) o).getUserId())){
                isEqual = false;
            }else if(!productId.equals(((Review) o).getProductId())){
                isEqual = false;
            }else if(!leaseStart.equals(((Review) o).getLeaseStart())){
                isEqual = false;
            }else if(!leaseEnd.equals(((Review) o).getLeaseEnd())){
                isEqual = false;
            }else if(!imageUrl.equals(((Review) o).getImageUrl())){
                isEqual = false;
            }
        }
        else{
            isEqual = false;
        }
        return isEqual;
    }

}
