package com.test.spring;

import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2019/7/30.
 */
@Configuration
public class TestService {
    private String name = "spring testService";
    private int age = 23;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
