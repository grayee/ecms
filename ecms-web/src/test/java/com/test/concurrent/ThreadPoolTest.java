package com.test.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2019/1/25 0025.
 */
public class ThreadPoolTest {

    /**
     * 在一台机器上开多少个线程公式：nThreads=nCpu∗uCpu∗(1+W/C)
     * W是等待时间、C是使用CPU的计算时间 ,nCpu 为CPU个数，uCpu为目标cpu使用率,一般在50%左右
     */
    private static final int nThreads = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {
        CustomExceptionHandler  exceptionHandler = new CustomExceptionHandler();
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setUncaughtExceptionHandler(exceptionHandler)
            .setNameFormat("demo-pool-%d").build();

        /**
         * DiscardPolicy 直接丢弃任务而不抛出异常。这样fetch-线程就不会收到RejectedExecutionException 异常而阻塞
         * AbortPolicy 饱和策略,就是中止任务,丢弃任务并抛出RejectedExecutionException 异常，会导致线程阻塞，比如任务队列过小并发量大。
         * Caller-Runs 调用者运行策略，该策略不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
         *
         * 可以通过使用SynchronousQueue来避免任务排队,可以参考 Executor，newCachedThreadPool
         * 只有当任务相互独立时，为线程池或工作队列设置界限才是合理 的，如果任务之间存在依赖性，那么应该使用无界的newCacheThreadPool。否则很可能发生死锁，
         * 即正在执行的任务等待尚未开始执行任务的执行结果。
         *
         * 根据并发量多大？数据量多大，任务处理时间这些信息就大概能知道任务队列定义多长合适，默认的无界阻塞队列（Integer.MAX_VALUE）过大容易OOM，
         * 任务处理时间按短队列长度过小则容易发生拒绝和阻塞。
         */
        ExecutorService singleThreadPool = new ThreadPoolExecutor(2, nThreads,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10000; i++) {
            int finalI = i;

            Thread.sleep(30);
            singleThreadPool.execute(() -> System.out
                .println(finalI + "==>>" + Thread.currentThread().getName() + "==>" + Thread.currentThread().getId()));


            //ForkJoinPool
            CompletableFuture
                .runAsync(() -> System.out .println(finalI + "==>>" + Thread.currentThread().getName() + "==>" + Thread.currentThread().getId()));
        }

        singleThreadPool.shutdown();

        Thread.sleep(5000);

    }


    public static class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(String.format("Custom thread exception,thread name:%s,msg:%s", t.getName(), e.getMessage()));
        }
    }
}
