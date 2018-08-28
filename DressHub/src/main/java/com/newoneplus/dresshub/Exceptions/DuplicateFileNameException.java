package com.newoneplus.dresshub.Exceptions;

public class DuplicateFileNameException extends Exception {
    public DuplicateFileNameException(){
        super("파일이름이 중복되었습니다.");
    }
}

