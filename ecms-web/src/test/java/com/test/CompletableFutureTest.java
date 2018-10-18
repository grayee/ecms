package com.test;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/**
 * http://colobu.com/2016/02/29/Java-CompletableFuture/
 *
 * Created by zhangruigang on 2017/9/19.
 * CompletableFuture 代表着一个 Future 完成后该干点什么，具体大致有： 1.Future
 * 完成后执行动作，或求取下一个 Future 的值。then... 2.多个 Future 的协调; 同时完成该怎么，其中一个完成该如何。allOf, anyO
 */
public class CompletableFutureTest {

    public static Logger logger = Logger.getLogger(CompletableFutureTest.class.getName());

    private static int PROCESSORS = Runtime.getRuntime().availableProcessors();

    @Test
    public void test() throws Exception {
      /*  test1();
        test11(false);
        test111(false);*/

        /**
         * 1.主动完成计算
         */
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 100;
        });
        //future.join();
        future.get();
        //future.get(1, TimeUnit.MINUTES);
        //future.getNow(10);

        /**
         * 2.创建CompletableFuture对象
         */
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            //长时间的计算任务
            return "·00";
        });

        CompletableFuture<Void> voidFuture = CompletableFuture.runAsync(() -> {
            System.out.println("0.00");
        });

        /**
         * 3.计算结果完成时的处理
         */
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(RandomUtils::nextInt)
            .whenComplete((v, e) -> {
                System.out.println(v);
                System.out.println(e);
            }).exceptionally(e -> e.hashCode());

        /**
         * 4.转换
         */

        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> f = future4.thenApplyAsync(i -> i * 10).thenApply(i -> i.toString());
        System.out.println(f.get()); //"1000"

        CompletableFuture
            .allOf(Lists.newArrayList(future, future1, voidFuture, future3, future4).toArray(new CompletableFuture[5]));

    }

    private void test2() throws InterruptedException, java.util.concurrent.ExecutionException {
        /**
         * CompletableFuture 根据任务的主从关系为
         * 提交任务的方法，如静态方法 supplyAsync(supplier[, executor]),  runAsync(runnable[, executor]);supplyAsync有返回值，runAsync 没有返回值
         * 回调函数，即对任务执行后所作出回应的方法，多数方法了，如 thenRun(controller), thenRunAsync(controller[, executor]), whenComplete(controller), whenCompleteAsync(controller[, executor]) 等
         * 根据执行方法可分为同步与异步方法，任务都是要被异步执行，所以提交任务的方法都是异步的。而对任务作出回应的方法很多分为两个版本，如
         * 同步方法，如 thenRun(controller), whenComplete(controller)
         * 异步方法，如 thenRunAsync(controller[, executor]), whenCompleteAsync(controller[, executor]), 异步方法可以传入线程池，否则用默认的
         */
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "hello.world==>CompletableFuture.supplyAsync";
        });
        System.out.println(completableFuture.get());
        CompletionStage<Void> voidCompletableFuture = CompletableFuture
            .runAsync(() -> System.out.println("hello.world2"), Executors.newCachedThreadPool());

        //completableFuture 回调函数
        completableFuture.thenRun(() -> {
            System.out.println("test1:2 - thenRun(runnable)), controller thread: " + Thread.currentThread());
        });

        completableFuture.thenRunAsync(() -> {
            System.out.println("test1:3 - thenRunAsync(runnable), controller thread: " + Thread.currentThread());
        });
    }

    private static void sleep(long millis) {
        try {
            Thread.currentThread().sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test1() {
        AtomicReference<String> reference = new AtomicReference<>();
        logger.info("ready, " + reference.get());
        new Thread(() -> {
            //do something that is time-consuming
            sleep(3000);
            reference.set("I'm done");  //任务完成完设置 reference 的值
        }).start();

        while (reference.get() == null) { //耐心的等待，直到 reference.get() 有值为止
        }

        logger.info("Finally, " + reference.get());
    }

    public void test11(boolean exceptionOn) {
        /**
         * 现实中我们不会这么去构造并管理 CompletableFuture, 但下面的例子对我们理解它还是有帮助的。
         * 我们多用 supplyAsync(...) 静态方法来获得 CompletableFuture 实例，因为它同时给我们处理了 completeExceptionally(ex) 的细节,见test111
         */
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
            sleep(5000);
            /**
             * 如果在设置 CompletableFuture.complete(value) 之前出现了异常，那么 get() 或其他回调函数像 whenComplete() 都会无限期的等待下去
             * 办法一是调用 get(timeout) 时给定一个超时时间，如果指定时间内还没有获得结果则得到 TimeoutException。
             * 另一种办法是要在线程中通过 completeExceptionally(ex) 来传播异常
             */
            try {
                if (exceptionOn) {
                    throw new RuntimeException("Something wrong");
                }
                future.complete("I'm done");
            } catch (Exception ex) {
                future.completeExceptionally(ex); //捕获的异常还会由 ExecutionException 包裹一下
            }

        }).start();

        logger.info("do anything you want, 当前线程不被阻塞..............");
        //线程任务完成的话，执行回调函数，不阻塞后续操作
        future.whenComplete((result, throwable) -> {
            logger.info("Finally,future executed completed with result:" + result);
            //do something else
            sleep(1000);
        });

        logger.info("waiting for completed...");
        /*
         * 实际使用 CompletableFuture 时不调用 Future 接口的 get() 等方法，上面的引用类型可以改成 CompletationStage, 以免受 get() 等方法的干扰
         */
        try {
            logger.info("future completed..." + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void test111(boolean exceptionOn) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        CompletableFuture<String> futurePrice = CompletableFuture.supplyAsync(() -> {
            if (exceptionOn) {
                throw new RuntimeException("Something wrong");
            }
            return "I'm done";
        }, executor);

        executor.shutdown();
        logger.info(" CompletableFuture.supplyAsync:" + futurePrice.get());

    }
}
