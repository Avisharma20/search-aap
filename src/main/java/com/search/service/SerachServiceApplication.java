package com.search.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.search.service"})
public class SerachServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SerachServiceApplication.class, args);
    }

}
