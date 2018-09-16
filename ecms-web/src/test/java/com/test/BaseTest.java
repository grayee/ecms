package com.test;

import com.qslion.Startup;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ecms
 *
 * @author Gray/Z
 * @date 2018/4/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PropertySource("classpath:application.yml")
public abstract class BaseTest {
}
