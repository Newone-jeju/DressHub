package com.newoneplus.dresshub;


import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RestfulExample {
    @RequestMapping("/")
    public String String() {
        return "Greetings from Spring Boot!";
    }
}
