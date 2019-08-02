package com.test.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Administrator on 2019/7/30.
 */
@Configuration
public class AppContext {
    /**
     * @Bean 注解的initMethod 和 destroyMethod。它们定义了生命周期方法的设置和清除,
     * @Scope 注解为 Course bean 定义了一个 prototype 范围。默认范围是 singleton
     */
    @Bean(initMethod = "setup", destroyMethod = "cleanup")
    @Scope("prototype")
    public Course course() {
        Course course = new Course();
        course.setModule(module());
        return course;
    }
    @Bean
    public Module module() {
        Module module = new Module();
        module.setAssignment("assignment");
        return module;
    }

}

class Module {

    private String assignment;

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }
}

class Course {

    private Module module;
    private String name;

    public void setup() {
        this.name = "M100 Pythagoras Theorems";
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void cleanup() {
        module = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


