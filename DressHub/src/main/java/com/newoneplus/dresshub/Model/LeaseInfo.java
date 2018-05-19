package com.newoneplus.dresshub.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//TODO 클린코드에서 읽은바로는 쓰지 말라고는 했는데 이유 다시 알이보기
@EqualsAndHashCode(exclude = "id")
public class LeaseInfo {
    private int id;
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

//
//    @Override
//    public boolean equals(Object o){
//        boolean isEqual = true;
//        //아이디 빼고 모든 자료형..
//
//        if (o instanceof LeaseInfo) {
//            if (!leaseDay.equals(((LeaseInfo) o).getLeaseDay())) {
//                isEqual = false;
//            } else if (!totalPrice.equals(((LeaseInfo) o).getTotalPrice())) {
//                isEqual = false;
//            } else if (!leaseSite.equals(((LeaseInfo) o).getLeaseSite())) {
//                isEqual = false;
//            }  else if (!leaser.equals(((LeaseInfo) o).getLeaser())) {
//                isEqual = false;
//            } else if (!product.equals(((LeaseInfo) o).getProduct())) {
//                isEqual = false;
//            } else if (!returnDay.equals(((LeaseInfo) o).getReturnDay())){
//                isEqual = false;
//            } else if (!returnSite.equals(((LeaseInfo) o).getReturnSite())){
//                isEqual = false;
//            }
//        } else{
//            isEqual = false;
//        }
//        return isEqual;
//    }
}
