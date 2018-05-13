package com.newoneplus.dresshub.Model;

import lombok.Data;

@Data
public class LeaseInfo {
    private int id;
    private String leaseDay;
    private String returnDay;
    private Integer totalPrice;
    private String leaseSite;
    private String returnSite;
    private String leaser;
    private Integer product;


    @Override
    public boolean equals(Object o){
        boolean isEqual = true;
        //아이디 빼고 모든 자료형..

        if (o instanceof LeaseInfo) {
            if (!leaseDay.equals(((LeaseInfo) o).getLeaseDay())) {
                isEqual = false;
            } else if (!totalPrice.equals(((LeaseInfo) o).getTotalPrice())) {
                isEqual = false;
            } else if (!leaseSite.equals(((LeaseInfo) o).getLeaseSite())) {
                isEqual = false;
            }  else if (!leaser.equals(((LeaseInfo) o).getLeaser())) {
                isEqual = false;
            } else if (!product.equals(((LeaseInfo) o).getProduct())) {
                isEqual = false;
            } else if (!returnDay.equals(((LeaseInfo) o).getReturnDay())){
                isEqual = false;
            } else if (!returnSite.equals(((LeaseInfo) o).getReturnSite())){
                isEqual = false;
            }
        } else{
            isEqual = false;
        }
        return isEqual;
    }
}
