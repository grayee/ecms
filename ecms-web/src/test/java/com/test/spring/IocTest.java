package com.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2019/7/30.
 */
public class IocTest {

    public static void main(String[] args) {
        //在传统 XML 方法中，使用 ClassPathXmlApplicationContext 类来加载外部 XML 上下文文件
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        TestService testService = (TestService) context.getBean("testService");

        System.out.println("testService.name = " + testService.getName());

        //基于 Java 的配置 或者注解（AnnotatedBeanDefinitionReader）、类路径（ClassPathBeanDefinitionScanner）
        context = new AnnotationConfigApplicationContext(TestService.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println("BeanDefinitionNames:" + beanName);
        }
        testService = context.getBean(TestService.class);
        System.out.println("testService.age = " + testService.getAge());

        //此处的配置类是使用 @Configuration 注释声明的 AppContext
        context = new AnnotationConfigApplicationContext(AppContext.class);
        Course course = context.getBean(Course.class);
        System.out.println("Course.name = " + course.getName());
        Module module = context.getBean(Module.class);
        System.out.println("module.assignment = " + module.getAssignment());
    }



}
