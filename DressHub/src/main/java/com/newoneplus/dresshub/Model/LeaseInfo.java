package com.newoneplus.dresshub.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.HashMap;

@Data
@Entity
public class LeaseInfo {
    @Id
    private Integer id;
    private String leaseDay;
    private String returnDay;
    private Integer totalPrice;
    private String leaseSite;
    private String returnSite;
    private String leaser;
    private Integer product;
    private String status; //주문, 주문대기, 출고, 배송, 대여중 , 배송, 세탁, 입고
    private String orderManager;
    private String orderManagerCall;
    private String orderManagerMassage;
    private String orderStart;
    private String orderEnd;
    private String leaseDeliverManager;
    private String leaseDeliverManagerCall;
    private String leaseDeliverManagerMassage;
    private String leaseDeliverStart;
    private String leaseDeliverEnd;
    private String leaseManager;
    private String leaseManagerCall;
    private String leaseManagerMassage;
    private String leaseStart;
    private String leaseEnd;
    private String returnManager;
    private String returnManagerCall;
    private String returnManagerMassage;
    private String returnStart;
    private String returnEnd;
    private String laundryManager;
    private String laundryManagerCall;
    private String laundryManagerMassage;
    private String laundryStart;
    private String laundryEnd;
    private String ready;



}
