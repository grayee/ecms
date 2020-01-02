package com.qslion;

import com.qslion.web.accounting.service.AccountVoucherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator
 * on 2019/8/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AccountVoucherService accountVoucherService;
    @Test
    public void redisTest() {
        // redis存储数据
        String key = "name";
        redisTemplate.opsForValue().set(key, "testNameforRedis");
        // 获取数据
        String value = (String) redisTemplate.opsForValue().get(key);
        System.out.println("获取缓存中key为" + key + "的值为：" + value);

        System.out.println(accountVoucherService.generate("收"));
        System.out.println(accountVoucherService.generate("付"));
    }

}


