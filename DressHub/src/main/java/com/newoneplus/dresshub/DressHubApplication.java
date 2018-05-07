package com.newoneplus.dresshub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication

public class DressHubApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DressHubApplication.class, args);
        System.out.println("Spring boot start");
    }

    @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { return builder.sources(DressHubApplication.class); }
}
