package com.qslion.ecms.service;

import javax.xml.ws.Endpoint;

/**
 * Created by zhangruigang on 2016/9/24.
 */
public class WebServiceLaunch {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("web service start");
        BookService implementor = new BookService();
        String address = "http://localhost:8080/services/bookservice";
        Endpoint.publish(address, implementor);
        System.out.println("web service started");
/*
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        // 除了http://localhost部分其他部分可以随便写，但格式不能乱 http://IP:PORT/NAME
        factory.setAddress(address);
        factory.setServiceClass(BookService.class);//可省，但不建议，因为可能会有些小问题
        factory.setServiceBean(implementor);
//        //LoggingInInterceptor和LoggingOutInterceptor是日志拦截器，用于输入和输出时显示日志
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.create();
        System.out.println("ws is published");*/

    }
}
