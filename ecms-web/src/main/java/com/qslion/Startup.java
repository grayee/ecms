package com.qslion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Startup {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Startup.class, args);
    }
}