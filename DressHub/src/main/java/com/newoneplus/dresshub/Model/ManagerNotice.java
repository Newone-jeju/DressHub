package com.newoneplus.dresshub.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
public class ManagerNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "title")
    String title;
    @Column(name = "content")
    String content;
    @Column(name = "date")
    String date;  //0000-00-00 hh:mm:ss
    @Column(name = "writer")
    String writer;

}
