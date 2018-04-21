package com.newoneplus.dresshub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class DressHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(DressHubApplication.class, args);

        System.out.println("Spring boot start");
    }
}
