package com.test;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class CustomThreadPoolExecutor {

    private ThreadPoolExecutor pool = null;

    /**
     * 线程池初始化方法
     * <p>
     * corePoolSize 核心线程池大小:10
     * maximumPoolSize 最大线程池大小:30
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间:30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位:TimeUnit.MINUTES
     * workQueue 阻塞队列:new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列
     * threadFactory 新建线程工厂:new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize + workQueue之和时,
     * 即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),任务会交给RejectedExecutionHandler来处理
     */
    public void init() {
        pool = new ThreadPoolExecutor(10, 30, 30, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(10), new CustomThreadFactory(),
                new CustomRejectedExecutionHandler());
    }

    public void destory() {
        if (pool != null) {
            pool.shutdownNow();
        }
    }

    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }

    private class CustomThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = String.format("%s-%s", CustomThreadPoolExecutor.class.getSimpleName(), count.addAndGet(1));
            System.out.println(threadName);
            t.setName(threadName);
            return t;
        }
    }

    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 记录异常
            // 报警处理等
            System.out.println("rejected error.............");
            try {
                //核心改造点，由blockingqueue的offer改成put阻塞方法
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // 测试构造的线程池
    public static void main(String[] args) {
        CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
        // 1.初始化
        exec.init();
        ExecutorService pool = exec.getCustomThreadPoolExecutor();
        CountDownLatch cdl = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            System.out.println("提交第" + i + "个任务!");
            int finalI = i;
            pool.execute(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    cdl.countDown();
                }
                System.out.println("running====="+ finalI);
            });
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all执行完成!");

        // 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了
         exec.destory();
    }

    private static void testThreadPool() throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        ExecutorService threadPool2 = new ThreadPoolExecutor(10, 30, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10), new BasicThreadFactory.Builder()
                .namingPattern("my-thread-pool-%d").daemon(true).build());

        CountDownLatch cdl = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            System.out.println("第" + i + "次执行");
            int finalI = i;
            threadPool2.execute(() -> {
                System.out.println("线程"+Thread.currentThread().getName()+"开始执行执行!");
                Future<Boolean> tpRelResult = threadPool2.submit(() -> {
                    System.out.println("Future task 【1】...."+Thread.currentThread().getName());
                    return true;
                });

                Future<Boolean> t2 = threadPool2.submit(() -> {
                    //Thread.currentThread().sleep(5000);
                    System.out.println("Future task 【2】...."+Thread.currentThread().getName());
                    return true;
                });

                try {
                    if (tpRelResult.get() && t2.get()) {
                        System.out.println("Future task all completed");
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();
                }

            });
        }
        cdl.await();
        System.out.println("all执行完成!");
    }
}
