package com.newoneplus.dresshub.Model;


import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
public class ApiResponseMessage {
    private HttpStatus status;
    private int result_code;
    private String message;
    private String debugMessage;
    public ApiResponseMessage()
    {

    }
    public ApiResponseMessage(HttpStatus status)
    {
        this.status = status;
    }

    public ApiResponseMessage(HttpStatus status, int result_code)
    {
        this.status = status;
        this.result_code = result_code;
    }

    public ApiResponseMessage(HttpStatus status, int result_code, Throwable ex){
        this.status = status;
        this.result_code = result_code;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiResponseMessage(HttpStatus status, String message, int result_code, Throwable ex){
        this.status = status;
        this.message = message;
        this.result_code = result_code;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
