package com.qslion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/13.
 */
@SpringBootApplication
public class ConfigApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ConfigApp.class, args);
    }

    @RestController
    @RefreshScope
    public class ConfigController {

        @Value("${user.name}")
        String userName;

        @Value("${user.age}")
        int age;

        @RequestMapping("/userinfo")
        public String get() {
            return userName + ":" + age;
        }
    }

}
