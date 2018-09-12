package com.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangruigang on 2017/12/26.
 */
public class ReentrantLockTest {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        lock.lock();
        lock.tryLock();

        try {
            System.out.println("lock test");
        } finally {
            lock.unlock();
        }
    }
}
