package com.newoneplus.dresshub.Exceptions;

public class NoResourcePresentException extends Exception {
    public NoResourcePresentException(){
        super("자원이 없습니다.");
    }
}
