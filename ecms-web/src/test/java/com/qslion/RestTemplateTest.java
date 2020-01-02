package com.qslion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator
 * on 2019/8/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;
    @Test
    public void contextLoads() {
        Object obj =  restTemplate.getForObject("http://localhost:8081/ecms/auth/au/user/detail/1?access_token=6ea2e741-cdd9-4244-98f8-66609cc978a1", Object.class);
        System.out.println(obj.toString());
    }

}


