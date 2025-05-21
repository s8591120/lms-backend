package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebManagementApplication.class, args);
    }

}
