package com.newoneplus.dresshub.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Field;
import java.util.HashMap;

@Data
//TODO 클린코드에서 읽은바로는 쓰지 말라고는 했는데 이유 다시 알이보기
@EqualsAndHashCode(exclude = "id")
public class LeaseInfo {
    private Integer id;
    private String leaseDay;
    private String returnDay;
    private Integer totalPrice;
    private String leaseSite;
    private String returnSite;
    private String leaser;
    private Integer product;
    private String status; //주문, 주문대기, 출고, 배송, 대여중 , 배송, 세탁, 입고
    private String orderTime;
    private String orderWaitStart;
    private String orderWaitEnd;
    private String releaseTime;
    private String leaseDeliverStart;
    private String leaseDeliverEnd;
    private String leaseStart;
    private String leaseEnd;
    private String returnStart;
    private String returnEnd;
    private String laundryStart;
    private String laundryEnd;
    private String ready;

    public HashMap<String, Object> toMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        for (Field e: this.getClass().getDeclaredFields()){
            try {
                hashMap.put(e.getName(), e.get(this));
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }
        return hashMap;
    }
}
