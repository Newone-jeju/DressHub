package com.newoneplus.dresshub.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class LeaseInfoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer leaseInfo;
    String status;
    String name;
    String phoneNum;
    String startDay;
    String endDay;
    String message;
}
