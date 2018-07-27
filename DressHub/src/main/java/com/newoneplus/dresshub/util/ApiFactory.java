package com.newoneplus.dresshub.util;

import com.newoneplus.dresshub.Model.ApiResponseMessage;

public class ApiFactory {

    public static ApiResponseMessage accept(){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setResult_code(200);
        apiResponseMessage.setMessage("승인");
        return apiResponseMessage;
    }

    public static ApiResponseMessage notLogined(){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setResult_code(404);
        apiResponseMessage.setMessage("로그인이 되어있지 않습니다.");
        return apiResponseMessage;
    }

    public static ApiResponseMessage noAuthority(){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setResult_code(404);
        apiResponseMessage.setMessage("접근 권한이 없습니다.");
        return apiResponseMessage;
    }

    public static ApiResponseMessage isEmpty(){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setResult_code(404);
        apiResponseMessage.setMessage("요청하신 자원이 없습니다.");
        return apiResponseMessage;
    }
}
