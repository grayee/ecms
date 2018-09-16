package com.qslion.ecms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.qslion")
public class Startup {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Startup.class, args);
    }
}