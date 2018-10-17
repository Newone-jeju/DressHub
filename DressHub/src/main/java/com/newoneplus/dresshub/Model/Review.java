package com.newoneplus.dresshub.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

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
    @Column(name = "product")
    Integer productId;
    String leaseStart; //0000-00-00
    String leaseEnd; //0000-00-00
    String imageUrl;
    @Column(name = "userUid")
    String user;
    @Transient
    String image;
}
