package com.newoneplus.dresshub.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
//@ToString
public class LeaseInfoLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private Integer leaseInfo;
    private String status;
    private String name;
    private String phoneNum;
    private String startDay;
    private String endDay;
    private String message;
}
