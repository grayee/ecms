package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zhangruigang on 2017/1/14.
 */
public class DisruptorTest {

    public static void main(String[] args) {
        AtomicBoolean update = new AtomicBoolean(false);
        ExecutorService pool= Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {

             //10 个线程调用test 方法。。。。
            final int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"==>>"+update+"------------"+finalI);
                test(update, finalI);

            }).start();
        }
    }

    public static void test(AtomicBoolean update,int ...param){
        //dao ....
        if (!update.get()){
            param[0]++;
            update.getAndSet(true);
            System.out.println("result------>>"+param[0]);
        }

    }
}
