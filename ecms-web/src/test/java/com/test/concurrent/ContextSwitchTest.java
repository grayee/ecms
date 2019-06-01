package com.test.concurrent;

/**
 * 上下文切换造成的影响
 *
 * @author Gray.Z
 * @date 2019/6/1 0001.
 */
public class ContextSwitchTest {

    private static final long count = 10000000;

    public static void main(String[] args) throws Exception {
        concurrency();
        series();
    }

    /**
     * 并发执行
     */
    private static void concurrency() throws Exception {
        long start = System.currentTimeMillis();
        //创建线程执行a+=
        Thread thread = new Thread(() -> {
            int a = 0;
            for (int i = 0; i < count; i++) {
                a += 1;
            }
        });
        //启动线程执行
        thread.start();
        //使用主线程执行b--;
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        //合并线程，统计时间
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("Concurrency：" + time + "ms, b = " + b);
    }

    /**
     * 串联执行
     */
    private static void series() {
        long start = System.currentTimeMillis();
        int a = 0, b = 0;
        for (long i = 0; i < count; i++) {
            a += 1;
        }
        for (int i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("Serial：" + time + "ms, b = " + b + ", a = " + a);
    }
}
