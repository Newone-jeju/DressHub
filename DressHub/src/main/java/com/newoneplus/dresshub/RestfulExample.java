package com.newoneplus.dresshub;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulExample {
    @RequestMapping("/")
    public String String() {
        return "Greetings from Spring Boot!";
    }
}
