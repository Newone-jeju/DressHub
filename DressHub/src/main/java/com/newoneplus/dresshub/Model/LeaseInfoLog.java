package com.newoneplus.dresshub.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class LeaseInfoLog {
    @Id
    Integer id;
    Integer leaseInfo;
    String status;
    String name;
    String phoneNum;
    String startDay;
    String endDay;
    String message;
}
