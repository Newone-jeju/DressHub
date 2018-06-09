package com.newoneplus.dresshub.Model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Data
@Entity
public class Review {
    @Id
    Integer id;
    String title;
    String comment;
    Float rate;
    String date;  //0000-00-00 hh:mm:ss
    String user;
    Integer productId;
    String leaseStart; //0000-00-00
    String leaseEnd; //0000-00-00
    String imageUrl;
}
