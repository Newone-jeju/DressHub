package com.newoneplus.dresshub.Model;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String title;
    String comment;
    Float rate;
    String date;  //0000-00-00 hh:mm:ss
    String user;
    @Column(name = "product")
    Integer productId;
    String leaseStart; //0000-00-00
    String leaseEnd; //0000-00-00
    String imageUrl;
}