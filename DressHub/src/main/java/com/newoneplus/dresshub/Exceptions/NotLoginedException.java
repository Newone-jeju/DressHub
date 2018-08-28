package com.newoneplus.dresshub.Exceptions;

import java.nio.file.NotLinkException;

public class NotLoginedException extends Exception {
    public NotLoginedException(){
        super("토큰이 없습니다.");
    }
}
