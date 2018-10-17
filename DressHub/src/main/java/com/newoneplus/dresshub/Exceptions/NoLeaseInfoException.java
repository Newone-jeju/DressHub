package com.newoneplus.dresshub.Exceptions;

public class NoLeaseInfoException  extends Exception{
    public NoLeaseInfoException(){
        super("대여 내역이 없습니다.");
    }
}
