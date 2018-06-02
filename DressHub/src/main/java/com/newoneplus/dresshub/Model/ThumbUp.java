package com.newoneplus.dresshub.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ThumbUp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany
    String user;
    @OneToMany
    int product;
}
